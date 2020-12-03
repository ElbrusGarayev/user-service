package com.userservice;

import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.sql.DataSource;


@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

//	@Bean
//	public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
//		var dsv = new DatabaseStartupValidator();
//		dsv.setDataSource(dataSource);
//		dsv.setValidationQuery(DatabaseDriver.POSTGRESQL.getValidationQuery());
//		return dsv;
//	}

}
