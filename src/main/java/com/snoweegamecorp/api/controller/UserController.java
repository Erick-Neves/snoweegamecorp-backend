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

/**
 * Controller class for handling user-related endpoints.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * Endpoint to create a new user.
     *
     * @param user The user object to be created.
     * @param result BindingResult for validation errors.
     * @return ResponseEntity with the created UserDTO.
     */
    @PostMapping("/create")
    public ResponseEntity<UserDTO> insertNewUser(@Valid @RequestBody User user, BindingResult result){
        UserDTO userDTO = service.createUser(user);
        return ResponseEntity.status(201).body(userDTO);
    }

    /**
     * Endpoint to get all users.
     *
     * @return ResponseEntity with a list of all UserDTOs.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOS = service.findAllUsers();
        return ResponseEntity.ok().body(userDTOS);
    }

    /**
     * Endpoint to get a user by id.
     *
     * @param id The id of the user.
     * @return ResponseEntity with the UserDTO of the specified id.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        UserDTO userDTO = service.findUserById(id);
        return ResponseEntity.ok().body(userDTO);
    }

    /**
     * Endpoint to get a user by username.
     *
     * @param username The username of the user.
     * @return ResponseEntity with the UserDTO of the specified username.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        UserDTO userDTO = service.findUserByUsername(username);
        return ResponseEntity.ok().body(userDTO);
    }

    /**
     * Endpoint to update a user.
     *
     * @param user The user object to be updated.
     * @return ResponseEntity with the updated UserDTO.
     */
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        UserDTO updatedUser = service.updateUser(user);
        return ResponseEntity.status(202).body(updatedUser);
    }

    /**
     * Endpoint to delete a user by id.
     *
     * @param id The id of the user to be deleted.
     * @return ResponseEntity indicating successful deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Integer id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}