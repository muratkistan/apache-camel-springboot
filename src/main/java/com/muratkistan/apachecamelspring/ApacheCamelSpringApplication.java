package com.muratkistan.apachecamelspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.muratkistan.apachecamelspring.beans")
public class ApacheCamelSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelSpringApplication.class, args);
	}

}
