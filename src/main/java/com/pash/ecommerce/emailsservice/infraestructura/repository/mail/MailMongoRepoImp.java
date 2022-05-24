package com.pash.ecommerce.emailsservice.infraestructura.repository.mail;

import com.pash.ecommerce.emailsservice.domain.model.entity.MailEntity;
import com.pash.ecommerce.emailsservice.domain.repository.MailRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MailMongoRepoImp implements MailRepository {

    private final MailMongoRepository repository;

    public MailMongoRepoImp(MailMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<MailEntity> saveLogMail(MailEntity mail) {
        return repository.save((MailMongoEntity) mail).cast(MailEntity.class);
    }
}
