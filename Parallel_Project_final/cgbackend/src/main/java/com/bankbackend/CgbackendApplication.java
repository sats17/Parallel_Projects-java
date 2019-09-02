package com.bankbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CgbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CgbackendApplication.class, args);
		System.out.println("port working");
	}

}
