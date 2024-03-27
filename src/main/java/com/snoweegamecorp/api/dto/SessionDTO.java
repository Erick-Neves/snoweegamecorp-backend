// This package contains the SessionDTO class which is used to transfer data about a session between different parts of the application.
package com.snoweegamecorp.api.dto;

/**
 * SessionDTO class is a Data Transfer Object for Session.
 * It is used to transfer data between different parts of the application.
 */
public class SessionDTO {

    // The login of the session
    private String login;

    // The token of the session
    private String token;

    /**
     * Get the login of the session.
     * @return The login of the session.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the login of the session.
     * @param login The login to set.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the token of the session.
     * @return The token of the session.
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the token of the session.
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}