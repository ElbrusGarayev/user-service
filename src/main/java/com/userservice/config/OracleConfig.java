//package com.userservice.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@Slf4j
//public class OracleConfig {
//
//    @Bean(name = "dataSourceProperties")
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "dataSource")
//    @ConfigurationProperties("spring.datasource.hikari")
//    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties campaignDataSourceProperties) {
//        return campaignDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .driverClassName(campaignDataSourceProperties.getDriverClassName())
//                .url(campaignDataSourceProperties.getUrl())
//                .username(campaignDataSourceProperties.getUsername())
//                .password(campaignDataSourceProperties.getPassword())
//                .build();
//    }
//}
