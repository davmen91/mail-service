 package com.pash.ecommerce.emailsservice.infraestructura.repository.mail;

import com.pash.ecommerce.emailsservice.domain.model.entity.MailEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter @ToString
@Component("MailEntity")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document(collection = "log-mail")
public class MailMongoEntity implements MailEntity {

    @Id
    private String id;

    private String from;

    private String to;

    private String subject;

    private String body;

    private String filename;

    @CreatedDate
    @Field(name = "creation_date")
    private LocalDateTime creationDate;

    public MailMongoEntity build(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;

        return this;
    }
}