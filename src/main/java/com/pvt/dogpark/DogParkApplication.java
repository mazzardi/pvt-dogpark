package com.pvt.dogpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DogParkApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DogParkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DogParkApplication.class, args);
	}

}
