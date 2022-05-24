package com.pash.ecommerce.emailsservice.domain.repository;

import com.pash.ecommerce.emailsservice.domain.model.entity.MailEntity;
import reactor.core.publisher.Mono;

public interface MailRepository {

    Mono<MailEntity> saveLogMail(MailEntity mail);


}
