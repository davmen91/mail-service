package com.pash.ecommerce.emailsservice.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class DataRequest implements Serializable {

    private String to;
    private String body;
    private String subject;

    private static final long serialVersionUID = -109972118373011419L;
}
