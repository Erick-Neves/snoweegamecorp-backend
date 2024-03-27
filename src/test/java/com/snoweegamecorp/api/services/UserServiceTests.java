package com.snoweegamecorp.api.services;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.service.UserService;
import com.snoweegamecorp.api.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for UserService
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private User user;
    private UserDTO userDTO;

    /**
     * Initialize user object before each test
     */
    @BeforeEach
    public void init(){
        user = TestUtils.instantiateNewUser(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Test for creating a new user and returning the new UserDTO
     */
    @Test
    public void UserService_CreateUser_ReturnNewUserDTO(){
        when(userRepository.save(user)).thenReturn(user);
        userDTO = userService.createUser(user);
        Assertions.assertThat(userDTO.getName().equals(user.getName()) && userDTO.getPassword() == null);
    }

    /**
     * Test for finding all users and returning a list of UserDTOs
     */
    @Test
    public void UserService_FindAllUsers_ReturnListUsersDTO(){
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(TestUtils.instantiateNewUser(2));
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOS = userService.findAllUsers();
        Assertions.assertThat(userDTOS.get(0).getUsername().equals(user.getUsername()) &&
                userDTOS.get(0).getPassword() == null &&
                userDTOS.stream().count() == 2);
    }

    /**
     * Test for finding a user by its id and returning the UserDTO
     */
    @Test
    public void UserService_FindUserById_ReturnUserDTO(){
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        userDTO = userService.findUserById(user.getId());
        Assertions.assertThat(userDTO.getId().equals(user.getId()) && userDTO.getPassword() == null);
    }

    /**
     * Test for finding a user by its username and returning the UserDTO
     */
    @Test
    public void UserService_FindUserByUsername_ReturnUserDTO(){
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        userDTO = userService.findUserByUsername(user.getUsername());
        Assertions.assertThat(userDTO.getUsername().equals(user.getUsername()) && userDTO.getPassword() == null);
    }

    /**
     * Test for updating a user and returning the UserDTO
     */
    @Test
    public void UserService_UpdateUser_ReturnUserDTO(){
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        userDTO = userService.findUserByUsername(user.getUsername());
        Assertions.assertThat(userDTO.getUsername().equals(user.getUsername()) && userDTO.getPassword() == null);
    }

    /**
     * Test for deleting a user
     */
    @Test
    public void UserService_DeleteUser_ReturnUserDTO(){
        doNothing().when(userRepository).deleteById(user.getId());
        userService.deleteUser(1);
        Assertions.assertThat(userRepository.findById(user.getId()) == null);
    }
}