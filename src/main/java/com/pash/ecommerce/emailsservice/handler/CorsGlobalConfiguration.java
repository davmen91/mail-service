package com.pash.ecommerce.emailsservice.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("https://www.patprimo.com", "https://www.sevenseven.com", "https://www.facol.com.co", "https://www.qapatprimo.myvtex.com")
                .allowedMethods("POST")
                .maxAge(3600);
    }
}