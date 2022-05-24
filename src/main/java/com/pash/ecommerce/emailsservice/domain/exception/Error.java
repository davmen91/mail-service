package com.pash.ecommerce.emailsservice.domain.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error extends Throwable implements Serializable {

    @JsonProperty("class")
    private String clazz;

    private String message;

    @JsonProperty("cause")
    private String origin;

    private int code;

    private Error error;

    public Error(String clazz, String message, String cause) {
        super(message);
        this.clazz = clazz;
        this.message = message;
        this.origin = cause;
    }

    public Error(String clazz, String message, String cause, int code) {
        super(message);
        this.clazz = clazz;
        this.message = message;
        this.origin = cause;
        this.code = code;
    }


    @Override
    public String toString() {
        return "{ \"error\": {"
                .concat("\"message\": ")
                .concat("\"" + replaceQuotes(message) + "\"")
                .concat(", \"cause\": ")
                .concat("\"" + replaceQuotes(origin) + "\"")
                .concat("} }");
    }

    private String replaceQuotes(String paragraph) {
        return paragraph.replaceAll("\"", "'");
    }

    private static final long serialVersionUID = -7039274842224548153L;
}
