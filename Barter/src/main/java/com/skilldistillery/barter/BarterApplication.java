package com.skilldistillery.barter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarterApplication.class, args);
	}

	@Bean
	public PasswordEncoder configurePasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
