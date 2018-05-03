package com.series;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeriesApplication.class, args);
	}
}
