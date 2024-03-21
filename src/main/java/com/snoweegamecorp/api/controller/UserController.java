package com.snoweegamecorp.api.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/create")
    public ResponseEntity<UserDTO> insertNewUser(@Valid @RequestBody User user, BindingResult result){
        UserDTO userDTO = service.createUser(user);
        return ResponseEntity.status(201).body(userDTO);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOS = service.findAllUsers();
        return ResponseEntity.ok().body(userDTOS);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        UserDTO userDTO = service.findUserById(id);
        return ResponseEntity.ok().body(userDTO);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        UserDTO userDTO = service.findUserByUsername(username);
        return ResponseEntity.ok().body(userDTO);
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        UserDTO updatedUser = service.updateUser(user);
        return ResponseEntity.status(202).body(updatedUser);
    }
}
