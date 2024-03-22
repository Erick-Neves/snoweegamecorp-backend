package com.snoweegamecorp.api.security.jwt;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class represents a JSON Web Token (JWT) object.
 * It contains the subject, issued at and expiration dates, and roles for the token.
 */
public class JWTObject {
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;
    /**
     * Sets the roles for the token.
     * @param roles The roles to be set.
     */
    public void setRoles(String... roles){
        this.roles = Arrays.asList(roles);
    }
    /**
     * Gets the subject of the token.
     * @return The subject of the token.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Sets the subject of the token.
     * @param subject The subject to be set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * Gets the issued at date of the token.
     * @return The issued at date of the token.
     */
    public Date getIssuedAt() {
        return issuedAt;
    }
    /**
     * Sets the issued at date of the token.
     * @param issuedAt The issued at date to be set.
     */
    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
    /**
     * Gets the expiration date of the token.
     * @return The expiration date of the token.
     */
    public Date getExpiration() {
        return expiration;
    }
    /**
     * Sets the expiration date of the token.
     * @param expiration The expiration date to be set.
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    /**
     * Gets the roles of the token.
     * @return The roles of the token.
     */
    public List<String> getRoles() {
        return roles;
    }
    /**
     * Sets the roles of the token.
     * @param roles The roles to be set.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}