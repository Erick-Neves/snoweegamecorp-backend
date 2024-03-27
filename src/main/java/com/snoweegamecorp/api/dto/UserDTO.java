package com.snoweegamecorp.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snoweegamecorp.api.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a User.
 * This class extends the User model and adds additional fields for DTO purposes.
 */
public class UserDTO extends User {
    // Unique identifier for the user
    private Integer id;

    // User's full name
    private String name;

    // User's username
    private String username;

    // URL of the user's profile picture
    private String profilePicUrl;

    // The date and time the user was created
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    // The date and time the user was last updated
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;

    // The roles assigned to the user
    private List<String> roles = new ArrayList<>();

    /**
     * Default constructor required by Hibernate.
     */
    public UserDTO(){
    }

    /**
     * Constructor to create a UserDTO from a User model.
     * @param user The User model to create the DTO from.
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.profilePicUrl = user.getProfilePicUrl();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.roles = user.getRoles();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProfilePicUrl() {
        return profilePicUrl;
    }
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
