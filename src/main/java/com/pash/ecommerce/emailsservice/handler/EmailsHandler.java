package com.pash.ecommerce.emailsservice.handler;

import com.pash.ecommerce.emailsservice.domain.model.MailRequest;
import com.pash.ecommerce.emailsservice.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;

import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.Map;

@Slf4j
@Controller
public class EmailsHandler {

    @Autowired
    private EmailSenderService sendersService;

    public Mono<ServerResponse> sendCV(ServerRequest request) {

        Mono<Map<String, Part>> monoMap = request.body(BodyExtractors.toMultipartData()).map(MultiValueMap::toSingleValueMap);
        Mono<MailRequest> monoRequest = monoMap.map(m -> {
            MailRequest mailRq = new MailRequest();
            if (m.containsKey("file")) mailRq.setFile((FilePart) m.get("file"));
            if (m.containsKey("data")) mailRq.setData((FormFieldPart) m.get("data"));
            return mailRq;
        });

        return sendersService.mailProcess(monoRequest);
    }
}
