package com.pash.ecommerce.emailsservice.domain.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //@RefreshScope
@Getter @Setter
@ConfigurationProperties(prefix = "cv")
public class CVConstant {

    private String from;
    private String to;
    private String body;
    private String subject;
    private String path;
    private Map<String, String> fields = new HashMap<>();


    public CVConstant() { }

    public void setTo(String to) {
        this.to = to;
        fields.put("to", to);
    }

    public void setBody(String body) {
        this.body = body;
        fields.put("body", body);
    }

    public void setSubject(String subject) {
        this.subject = subject;
        fields.put("subject", subject);
    }

    public void setFrom(String from) {
        this.from = from;
        fields.put("from", from);
    }

    public String   getField(String field) {
        return fields.get(field);
    }
}
