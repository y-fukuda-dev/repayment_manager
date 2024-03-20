package com.example.repayment_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
// (basePackages = "com.example.repayment_manager.repository")
public class RepaymentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepaymentManagerApplication.class, args);
	}

}
