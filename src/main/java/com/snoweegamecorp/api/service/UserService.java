package com.snoweegamecorp.api.service;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public UserDTO createUser(User user){
        String pass = user.getPassword();
        user.setPassword(
                passwordEncoder().
                        encode(pass));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        if (user.getProfilePicUrl() == "") {
            user.setProfilePicUrl("https://i.imgur.com/LGGL7VJ.png");
        }
        if (user.getRoles().stream().count() == 0){
            user.getRoles().add("USERS");
        }
        userRepository.save(user);
        return new UserDTO(user);
    }
    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTOToAdd = new UserDTO(user);
            userDTOS.add(userDTOToAdd);
        }
        return userDTOS;
    }
    public UserDTO findUserById(Integer id){
        User user = new User((userRepository.findById(id)));
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }
    public UserDTO findUserByUsername(String username){
        User user = new User(Optional.ofNullable((userRepository.findByUsername(username))));
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }
    public UserDTO updateUser(User user){
        User userFound = new User(Optional.ofNullable((userRepository.findByUsername(user.getUsername()))));
        userFound.setName(user.getName());
        userFound.setProfilePicUrl(user.getProfilePicUrl());
        userFound.setUpdatedAt(LocalDateTime.now());
        UserDTO userDTO = new UserDTO(userFound);
        return userDTO;
    }
}
