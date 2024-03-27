package com.snoweegamecorp.api;

import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.repository.UserRepositoryTests;
import com.snoweegamecorp.api.service.UserService;
import com.snoweegamecorp.api.services.UserServiceTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class for the Application.
 * This class tests the loading of the application context and the injection of the UserRepository and UserService.
 */
@ContextConfiguration(classes = {Application.class})
@ActiveProfiles("dev")
@SpringBootTest(classes = {UserRepositoryTests.class, UserServiceTests.class})
class ApplicationTests {
	@Autowired
	private ApplicationContext context; // The application context
	@Autowired
	private UserRepository userRepository; // The user repository
	@Autowired
	private UserService userService; // The user service

	/**
	 * Test method for the loading of the application context.
	 * This test method simply asserts that the application context is not null.
	 */
	@Test
	void contextLoads() {
		assertThat(context).isNotNull();
	}
}