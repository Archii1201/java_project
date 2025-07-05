package com.recipeplatform.recipePlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipePlatformApplication.class, args);
		System.out.println("Recipe Management Platform");
	}

}
