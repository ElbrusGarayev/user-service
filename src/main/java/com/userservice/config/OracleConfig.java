//package com.userservice.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "userServiceEntityManagerFactory",
//        transactionManagerRef = "userServiceTransactionManager",
//        basePackages = {"com.userservice.repository", "com.userservice.entity"})
//@Log4j2
//public class OracleConfig {
//
//    @Primary
//    @Bean(name = "userDataSourceProperties")
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean(name = "userDataSource")
//    @ConfigurationProperties("spring.datasource.hikari")
//    public DataSource dataSource(@Qualifier("userDataSourceProperties") DataSourceProperties loanDataSourceProperties) {
//        return loanDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
//                .driverClassName(loanDataSourceProperties.getDriverClassName())
//                .url(loanDataSourceProperties.getUrl())
//                .username(loanDataSourceProperties.getUsername())
//                .password(loanDataSourceProperties.getPassword())
//                .build();
//    }
//
//    @Bean(name = "userServiceEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("userDataSource") DataSource loanDataSource) {
//        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.implicit_naming_strategy",
//                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
//        properties.put("hibernate.physical_naming_strategy",
//                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        properties.put("hibernate.hbm2ddl.auto", "create");
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
//                .dataSource(loanDataSource)
//                .packages("com.userservice.entity")
//                .persistenceUnit("user-service")
//                .build();
//        localContainerEntityManagerFactoryBean.getJpaPropertyMap().putAll(properties);
//        return localContainerEntityManagerFactoryBean;
//    }
//
//    @Bean(name = "userServiceTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("userServiceEntityManagerFactory") EntityManagerFactory loanEntityManagerFactory) {
//        return new JpaTransactionManager(loanEntityManagerFactory);
//    }
//}
