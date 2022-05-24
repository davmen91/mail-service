package com.pash.ecommerce.emailsservice.infraestructura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MongoDBConfig Class
 * Configuration Mongo DB.
 * Java 11
 * @version 1.0
 */

@Configuration
@EnableReactiveMongoAuditing(dateTimeProviderRef = "dateTimeProvider")
public class MongoDBConfig {

    /**
     * customConversions method (Bean)
     * Custom Mongo conversion dates
     * @return MongoCustomConversions
     */
    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateToZonedDateTimeConverter());
        converters.add(new ZonedDateTimeToDateConverter());
        return new MongoCustomConversions(converters);
    }

    /**
     * DateToZonedDateTimeConverter Class
     * Custom converter time zone.
     * Java 11
     * @version 1.0
     */
    static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(Date source) {
            return ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    /**
     * ZonedDateTimeToDateConverter Class
     * Custom converter time zone..
     * Java 11
     * @version 1.0
     */
    static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime source) {
            return Date.from(source.toInstant());
        }
    }
}
