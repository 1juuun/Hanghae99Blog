package com.sparta.hanghae99_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hanghae99BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hanghae99BlogApplication.class, args);
	}

}
