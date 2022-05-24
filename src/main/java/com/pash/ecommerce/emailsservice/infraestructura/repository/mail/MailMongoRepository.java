package com.pash.ecommerce.emailsservice.infraestructura.repository.mail;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailMongoRepository extends ReactiveMongoRepository<MailMongoEntity, String> {
}
