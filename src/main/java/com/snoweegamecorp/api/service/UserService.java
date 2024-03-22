package com.snoweegamecorp.api.service;

import com.snoweegamecorp.api.dto.UserDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the service layer for user-related operations in the Snowee Game Corp API.
 * It provides methods for creating, updating, and retrieving user information.
 */
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    /**
     * Returns an instance of the BCryptPasswordEncoder class.
     *
     * @return the password encoder
     */
    @Autowired
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Creates a new user with the provided user details and returns a UserDTO object.
     * If the user's profile picture URL is not provided, it sets a default URL.
     * If the user does not have any roles assigned, it assigns the "USERS" role.
     *
     * @param user The user object containing the user details.
     * @return The UserDTO object representing the created user.
     */
    public UserDTO createUser(User user){
        // Encode the user's password
        String pass = user.getPassword();
        user.setPassword(getPasswordEncoder().encode(pass));
        // Set the creation and update timestamps
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        // Set a default profile picture URL if not provided
        if (user.getProfilePicUrl() == null || user.getProfilePicUrl().isEmpty()) {
            user.setProfilePicUrl("https://i.imgur.com/LGGL7VJ.png");
        }
        // Assign the "USERS" role if no roles are assigned
        if (user.getRoles().isEmpty()) {
            user.getRoles().add("USERS");
        }
        // Save the user to the repository and return the UserDTO object
        userRepository.save(user);
        return new UserDTO(user);
    }
    /**
     * Find all users and create a list of UserDTO objects.
     *
     * @return List of UserDTO objects
     */
    public List<UserDTO> findAllUsers(){
        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();
        // Create a list to store UserDTO objects
        List<UserDTO> userDTOS = new ArrayList<>();
        // Convert each User object to a UserDTO object and add it to the list
        for (User user : users) {
            UserDTO userDTOToAdd = new UserDTO(user);
            userDTOS.add(userDTOToAdd);
        }
        // Return the list of UserDTO objects
        return userDTOS;
    }
    /**
     * This method finds a user by their ID and returns the user as a DTO.
     *
     * @param id The ID of the user to find.
     * @return The user as a DTO.
     */
    public UserDTO findUserById(Integer id) {
        // Find the user by their ID
        User user = new User(userRepository.findById(id));
        // Create a DTO from the user
        UserDTO userDTO = new UserDTO(user);
        // Return the user as a DTO
        return userDTO;
    }
    /**
     * This method finds a user by their username and returns the user details in a UserDTO object.
     *
     * @param username The username of the user to find.
     * @return A UserDTO object containing the user details, or null if no user is found.
     */
    public UserDTO findUserByUsername(String username) {
        // Find the user by their username using the userRepository
        User User = userRepository.findByUsername(username);
        // Create a new UserDTO object from the found user
        UserDTO userDTO = new UserDTO(User);
        // Return the UserDTO object
        return userDTO;
    }
    /**
     * Updates the user information in the database.
     *
     * @param user The user object containing the updated information.
     * @return The updated user object in DTO format.
     */
    public UserDTO updateUser(User user) {
        // Find the user in the database by username
        User userFound = new User(
                Optional.ofNullable(userRepository.findByUsername(user.getUsername()))
        );
        // Update the user information
        userFound.setName(user.getName());
        userFound.setProfilePicUrl(user.getProfilePicUrl());
        userFound.setUpdatedAt(LocalDateTime.now());
        // Convert the updated user object to DTO format
        UserDTO userDTO = new UserDTO(userFound);
        return userDTO;
    }
    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Integer id){
        // Delete the user with the given ID from the repository
        userRepository.deleteById(id);
    }
}
