package com.prasanna.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionsApplication.class, args);
	}

}
