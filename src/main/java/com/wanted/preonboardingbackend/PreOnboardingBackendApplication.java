package com.wanted.preonboardingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PreOnboardingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreOnboardingBackendApplication.class, args);
	}

}
