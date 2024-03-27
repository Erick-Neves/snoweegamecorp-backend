package com.snoweegamecorp.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snoweegamecorp.api.controller.LoginController;
import com.snoweegamecorp.api.dto.LoginDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.security.SecurityConfig;
import com.snoweegamecorp.api.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LoginControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private SecurityConfig securityConfig;
    @Autowired
    private ObjectMapper objectMapper;
    private User user;
    private LoginDTO loginDTO;
    @BeforeEach
    public void init(){
        // Initialize user and userDTO objects
        user = TestUtils.instantiateNewUser(1);
        loginDTO = new LoginDTO(user.getUsername(), user.getPassword());
        SecurityConfig.EXPIRATION = 1800000L;
        SecurityConfig.KEY = "12345678901234567890123456789012";
        SecurityConfig.PREFIX = "Bearer ";
    }
    @Test
    public void LoginController_CreateUser_ReturnCreated_UserDTO() throws Exception {
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);
        ResultActions response = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO))
        );
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", CoreMatchers.is(user.getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }
}
