package com.project.Booktion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.project.Booktion")
@SpringBootApplication
public class BooktionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktionApplication.class, args);
	}

}
