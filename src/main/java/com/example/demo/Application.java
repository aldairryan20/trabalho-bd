package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public CommandLineRunner createDatabase() {
		return args -> {
			var sql = "CREATE DATABASE database";
		};
	}

	public CommandLineRunner createTables() {
		return args -> {
			var sql = "CREATE TABLE conta";
		};
	}
}
