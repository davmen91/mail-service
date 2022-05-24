package com.pash.ecommerce.emailsservice.infraestructura.config;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * CustomDateTimeProviderConfig Class
 * Custom time zone of date times .
 * Java 11
 * @version 1.0
 */

@Component("dateTimeProvider")
public class CustomDateTimeProviderConfig implements DateTimeProvider {
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(ZonedDateTime.now(ZoneId.of("America/Bogota")));
    }
}
