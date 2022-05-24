package com.pash.ecommerce.emailsservice.domain.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ProcessException extends RuntimeException {

    public ProcessException(Class<?> clazz, String message, Throwable e) {
        super(messageBuild(clazz, message, e));
        log.error("|>\t|>\t|>\t|>\t|>=E=R=R=O=R=> [ {} ] >> {} :: {} << \n\n\n", clazz, message, e.getMessage());
    }

    static Error messageBuild(Class<?> clazz, String message, Throwable t) {

        int code = 409;
        String cause = (t.getMessage() != null) ? t.getMessage() : Arrays.toString(t.getStackTrace());

        return new Error(clazz.getPackageName(), message, cause, code);
    }

    static Error mapper(String obj){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        try {
            return mapper.readValue(obj, Error.class);
        } catch (JsonProcessingException e) {
            String message = "The error model could not be mapped :: " + obj;
            return new Error(ProcessException.class.getPackageName(), message, e.getMessage());
        }
    }

    private static final long serialVersionUID = 607955690254601484L;
}
