package com.snoweegamecorp.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The main application class that starts the Spring Boot application.
 */
@SpringBootApplication
public class Application {
	/**
	 * The main method to start the Spring Boot application.
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
