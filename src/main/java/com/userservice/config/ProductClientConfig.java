package com.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.client.decoder.ProductClientErrorDecoder;
import feign.codec.ErrorDecoder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductClientConfig {

    final ObjectMapper objectMapper;

    @Bean
    public ErrorDecoder productClientErrorDecoder(){
        return new ProductClientErrorDecoder(objectMapper);
    }
}
