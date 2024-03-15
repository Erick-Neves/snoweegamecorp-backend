package com.snoweegamecorp.api.service;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
        this.userRepository.save(user);
        return new UserDTO(user);
    }
    public List<UserDTO> findAllUsers(){
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users){
            UserDTO userDTOToAdd = new UserDTO(user);
            userDTOS.add(userDTOToAdd);
        }
        return userDTOS;
    }
}
