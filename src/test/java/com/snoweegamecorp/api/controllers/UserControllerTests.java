package com.snoweegamecorp.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snoweegamecorp.api.controller.UserController;
import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.service.UserService;
import com.snoweegamecorp.api.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for UserController
 */
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
    private UserDTO userDTO;
    private User user;
    @BeforeEach
    public void init(){
        // Initialize user and userDTO objects
        user = TestUtils.instantiateNewUser(1);
        userDTO = new UserDTO(user);
        userDTO.setCreatedAt(LocalDateTime.now());
        userDTO.setUpdatedAt(LocalDateTime.now());
    }
    /**
     * Test for creating a user
     * @throws Exception
     */
    @Test
    public void UserController_CreateUser_ReturnCreated_UserDTO() throws Exception {
        when(userService.createUser(user)).thenReturn(userDTO);
        ResultActions response = mockMvc.perform(
                post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName())))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * Test for getting all users
     * @throws Exception
     */
    @Test
    public void UserController_GetAllUsers_ReturnOk_ListUserDTO() throws Exception {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        userDTOList.add(new UserDTO(TestUtils.instantiateNewUser(2)));
        when(userService.findAllUsers()).thenReturn(userDTOList);
        ResultActions response = mockMvc.perform(
                get("/users"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].username", CoreMatchers.is(user.getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * Test for getting a user by ID
     * @throws Exception
     */
    @Test
    public void UserController_GetUsersById_ReturnOk_UserDTO() throws Exception {
        when(userService.findUserById(user.getId())).thenReturn(userDTO);
        ResultActions response = mockMvc.perform(
                get("/users/id/"+user.getId()));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId())))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * Test for getting a user by username
     * @throws Exception
     */
    @Test
    public void UserController_GetUsersByUsername_ReturnOk_UserDTO() throws Exception {
        when(userService.findUserByUsername(user.getUsername())).thenReturn(userDTO);
        ResultActions response = mockMvc.perform(
                get("/users/username/"+user.getUsername()));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId())))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * Test to update user information in UserController
     *
     * @throws Exception
     */
    @Test
    public void UserController_UpdateUsers_ReturnAccepted_UserDTO() throws Exception {
        // Set user information
        user.setName("Taster");
        user.setProfilePicUrl("www.azeitona.com");

        // Set userDTO information
        userDTO.setName("Taster");
        userDTO.setProfilePicUrl("www.azeitona.com");

        // Mock the userService update method to return userDTO
        when(userService.updateUser(user)).thenReturn(userDTO);

        // Perform a PUT request to update user information
        ResultActions response = mockMvc.perform(
                put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));

        // Verify the response status is accepted and check returned user information
        response.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profilePicUrl", CoreMatchers.is(user.getProfilePicUrl())))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void UserController_DeleteUsersById_ReturnNoContent() throws Exception {
        // Stub the delete user method to do nothing
        doNothing().when(userService).deleteUser(user.getId());

        // Perform a delete request to delete the user by id
        ResultActions response = mockMvc.perform(delete("/users/delete/"+user.getId()));

        // Assert that the status is no content and print the result
        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}
