package com.snoweegamecorp.api.dto;

/**
 * Data transfer object for login information.
 */
public class LoginDTO {
    private String username; // the username
    private String password; // the password

    /**
     * Default constructor.
     */
    public LoginDTO() {
    }

    /**
     * Constructor with username and password parameters.
     * @param username the username
     * @param password the password
     */
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}