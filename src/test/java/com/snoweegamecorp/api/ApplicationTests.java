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

@ContextConfiguration(classes = {Application.class})
@ActiveProfiles("dev")
@SpringBootTest(classes = {UserRepositoryTests.class, UserServiceTests.class})
class ApplicationTests {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	}
}
