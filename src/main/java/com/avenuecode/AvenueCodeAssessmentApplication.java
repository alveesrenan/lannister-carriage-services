package com.avenuecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.avenuecode")
public class AvenueCodeAssessmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(AvenueCodeAssessmentApplication.class, args);
	}
}

