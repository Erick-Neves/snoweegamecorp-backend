package com.snoweegamecorp.api.services;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void UserService_CreateUser_ReturnNewUserDTO(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                4,
                "Tester",
                "tester4@gmail.com",
                "123456",
                "",
                roles );
        UserDTO userDTO = userService.createUser(user);
        Assertions.assertThat(userDTO).isNotNull();
    }
    @Test
    public void UserService_FindAllUsers_ReturnListUsersDTO(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user1 = new User(
                5,
                "Tester",
                "tester4@gmail.com",
                "123456",
                "",
                roles );
        userService.createUser(user1);
        User user2 = new User(
                5,
                "Tester",
                "tester5@gmail.com",
                "123456",
                "",
                roles );
        userService.createUser(user2);
        List<UserDTO> usersDTO = userService.findAllUsers();
        Assertions.assertThat(usersDTO.stream().count() == 2);
    }
}
