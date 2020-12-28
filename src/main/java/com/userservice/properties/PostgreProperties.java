package com.userservice.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "hibernate.properties")
@PropertySource(value = "classpath:application.yml")
public class PostgreProperties {

    String driverClassName;
    String url;
    String username;
    String password;

}
