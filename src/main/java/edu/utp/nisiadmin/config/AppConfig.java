package edu.utp.nisiadmin.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class ModelConfig {
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.featuresToDisable(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    DeserializationFeature.ACCEPT_FLOAT_AS_INT,
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}
