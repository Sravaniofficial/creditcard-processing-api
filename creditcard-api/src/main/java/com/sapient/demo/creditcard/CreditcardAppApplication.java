package com.sapient.demo.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CreditcardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditcardAppApplication.class, args);
	}

}
