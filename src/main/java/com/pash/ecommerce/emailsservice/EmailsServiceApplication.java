package com.pash.ecommerce.emailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EmailsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailsServiceApplication.class, args);
    }

}

