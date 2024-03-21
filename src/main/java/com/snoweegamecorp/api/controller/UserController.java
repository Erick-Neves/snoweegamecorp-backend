package com.snoweegamecorp.api.controller;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/create")
    public ResponseEntity insertNewUser(@Valid @RequestBody User user, BindingResult result){
        System.out.println(user.getPassword());
        UserDTO userDTO = service.createUser(user);
        System.out.println(result.hasErrors());
        return ResponseEntity.ok().body(userDTO);
    }
}
