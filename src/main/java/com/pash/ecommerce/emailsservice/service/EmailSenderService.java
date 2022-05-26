package com.pash.ecommerce.emailsservice.service;

import com.pash.ecommerce.emailsservice.domain.constants.CVConstant;
import com.pash.ecommerce.emailsservice.domain.model.DataRequest;
import com.pash.ecommerce.emailsservice.domain.model.MailRequest;
import com.pash.ecommerce.emailsservice.domain.model.entity.MailEntity;
import com.pash.ecommerce.emailsservice.domain.repository.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final MailRepository repository;
    private final CVConstant cvConstant;

    public EmailSenderService(JavaMailSender mailSender, MailRepository repository, CVConstant cvConstant) {
        this.mailSender = mailSender;
        this.repository = repository;
        this.cvConstant = cvConstant;
    }

    public Mono<ServerResponse> mailProcess(Mono<MailRequest> monoRequest) {
        return monoRequest.filter(rq ->
                rq.getData() != null || rq.getFile() != null)
                .flatMap(rq -> {
                    DataRequest data = requestValidate(rq.getData());
                    return this.sendEmailWithAttachment(data.getTo(), data.getSubject(), data.getBody(), rq.getFile())
                            .flatMap(response -> ServerResponse.ok().bodyValue(response));
                });
    }

    private Mono<String> sendEmailWithAttachment(String toEmail, String subject, String body, FilePart partFile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(cvConstant.getFrom());
            messageHelper.setTo(toEmail);
            messageHelper.setText(body, true);
            messageHelper.setSubject(subject);

            MailEntity entity = getMailEntity();
            entity.build(cvConstant.getFrom(), toEmail, subject, body);

            if (partFile != null) {
                File destiny = new File(cvConstant.getPath(), partFile.filename());
                    boolean save = destiny.createNewFile();
                    log.info("===> Create file :: {} = {}", destiny.getPath(), save);
                    return partFile.transferTo(destiny).doOnSuccess(a -> {
                        try {
                            entity.setFilename(partFile.filename());
                            messageHelper.addAttachment(partFile.filename(), destiny);
                        } catch ( MessagingException e) {
                            log.error("===> Error while file creation :: {}", e.getMessage());
                        }
                    }).then(SendingMessage(message, entity));
            }

            return SendingMessage(message, entity);
        } catch (Exception e) {
            return Mono.just("==> Error sending attachment mail: {} " + e);
        }

    }

    private Mono<String> SendingMessage(MimeMessage message, MailEntity entity) {
        return Mono.fromCallable(() -> {
            mailSender.send(message);
            repository.saveLogMail(entity).subscribe();
            return "Mail attachment sent successfully";
        });
    }

    private String dataValidate(String field, String value) {
        return value == null ? cvConstant.getField(field) : value;
    }

    private DataRequest requestValidate(DataRequest request) {

        if (request == null) return new DataRequest(cvConstant.getTo(), cvConstant.getBody(), cvConstant.getSubject());
        return new DataRequest(
                dataValidate("to", request.getTo()),
                dataValidate("body", request.getBody()),
                dataValidate("subject", request.getSubject())
        );
    }

    @Lookup
    public MailEntity getMailEntity() {
        return null;
    }
}
