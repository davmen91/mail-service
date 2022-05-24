package com.pash.ecommerce.emailsservice.handler.routes;

import com.pash.ecommerce.emailsservice.handler.EmailsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.http.MediaType.*;

@Slf4j
@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(EmailsHandler handler) {
        return route(POST("/cv").and(contentType(MULTIPART_FORM_DATA)), handler::sendCV);
    }
}
