package com.snoweegamecorp.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snoweegamecorp.api.controller.UserController;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActionsDsl;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    private User user1;
    private User user2;
    @Test
    public void UserController_CreateUser_ReturnCreated(){
        given(userService.createUser(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        //ResultActionsDsl resultActionsDsl = mockMvc.perform(post(""));
    }
}
