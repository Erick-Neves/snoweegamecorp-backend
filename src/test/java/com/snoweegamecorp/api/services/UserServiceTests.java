package com.snoweegamecorp.api.services;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.service.UserService;
import com.snoweegamecorp.api.utils.TestUtils;
import com.sun.source.tree.ModuleTree;
import org.assertj.core.api.Assertions;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
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
    private User user1;
    private User user2;
    private UserDTO userDTO1;
    private UserDTO userDTO2;
    @Test
    public void CreateUser_ReturnNewUserDTO(){
        user1 = TestUtils.instantiateNewUser(1);
        when(userRepository.save(user1)).thenReturn(user1);
        UserDTO userDTO = userService.createUser(user1);
        Assertions.assertThat(userDTO.getName().equals("Tester") && userDTO.getPassword() == null);
    }
    @Test
    public void FindAllUsers_ReturnListUsersDTO(){
        user1 = TestUtils.instantiateNewUser(1);
        user2 = TestUtils.instantiateNewUser(2);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userService.createUser(user1);
        userService.createUser(user2);
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOS = userService.findAllUsers();
        Assertions.assertThat(userDTOS.get(0).getUsername().equals("tester1@gmail.com") &&
                userDTOS.get(0).getPassword() == null &&
                userDTOS.stream().count() == 2);
    }
}
