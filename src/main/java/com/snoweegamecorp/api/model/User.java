package com.snoweegamecorp.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a user in the system.
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_users")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    /**
     * The name of the user.
     */
    @Column(nullable = false)
    @Size(min = 4, max = 50, message = "Name length must be between 4 and 50 characters!")
    @NotBlank
    private String name;
    /**
     * The username of the user.
     */
    @Column(unique = true, nullable = false)
    @Email(message = "Use a valid email!")
    @NotBlank
    private String username;
    /**
     * The password of the user.
     */
    @Column(nullable = false)
    @Size(min = 4, max = 100, message = "Password length must be between 4 and 100 characters!")
    @NotBlank
    private String password;
    /**
     * The URL of the user's profile picture.
     */
    @Column
    private String profilePicUrl;
    /**
     * The date and time when the user was created.
     */
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    /**
     * The date and time when the user was last updated.
     */
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;
    /**
     * The roles assigned to the user.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();
    /**
     * Constructs a new User object from an existing User object.
     * @param byId The existing User object.
     */
    public User(Optional<User> byId) {
        this.id = byId.get().getId();
        this.name = byId.get().getName();
        this.username = byId.get().getUsername();
        this.password = byId.get().getPassword();
        this.profilePicUrl = byId.get().getProfilePicUrl();
        this.createdAt = byId.get().getCreatedAt();
        this.updatedAt = byId.get().getUpdatedAt();
        this.roles = byId.get().getRoles();
    }
    /**
     * Gets the unique identifier for the user.
     * @return The unique identifier for the user.
     */
    public Integer getId() {
        return id;
    }
    /**
     * Sets the unique identifier for the user.
     * @param id The unique identifier for the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the roles of the user.
     *
     * @param roles The roles of the user.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Returns the roles of the user.
     *
     * @return The roles of the user.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the URL of the user's profile picture.
     *
     * @param profilePicUrl The URL of the user's profile picture.
     */
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    /**
     * Returns the URL of the user's profile picture.
     *
     * @return The URL of the user's profile picture.
     */
    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    /**
     * Sets the creation date and time of the user.
     *
     * @param createdAt The creation date and time of the user.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the creation date and time of the user.
     *
     * @return The creation date and time of the user.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the update date and time of the user.
     *
     * @param updatedAt The update date and time of the user.
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the update date and time of the user.
     *
     * @return The update date and time of the user.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
