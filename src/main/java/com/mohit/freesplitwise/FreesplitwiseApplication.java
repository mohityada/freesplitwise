package com.mohit.freesplitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FreesplitwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreesplitwiseApplication.class, args);
		System.out.println("Hello from, Spring");
	}

}
