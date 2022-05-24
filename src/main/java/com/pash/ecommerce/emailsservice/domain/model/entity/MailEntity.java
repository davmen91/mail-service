package com.pash.ecommerce.emailsservice.domain.model.entity;

public interface MailEntity {

    String getFrom();
    void setFrom(String from);
    String getTo();
    void setTo(String to);
    String getSubject();
    void setSubject(String subject);
    String getBody();
    void setBody(String body);
    String getFilename();
    void setFilename(String filename);

    MailEntity build(String from, String to, String subject, String body);
}
