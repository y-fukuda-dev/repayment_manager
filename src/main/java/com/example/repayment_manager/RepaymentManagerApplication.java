package com.example.repayment_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("com.example.repayment_manager.repository")
@ComponentScan
@EntityScan("com.example.repayment_manager.model")
// (basePackages = "com.example.repayment_manager.repository")
public class RepaymentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepaymentManagerApplication.class, args);
	}

}
