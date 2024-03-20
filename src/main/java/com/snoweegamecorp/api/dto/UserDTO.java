package com.snoweegamecorp.api.dto;

import com.snoweegamecorp.api.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDTO extends User {
    private Integer id;
    private String name;
    private String username;
    private String profilePicUrl;
    private List<String> roles = new ArrayList<>();

    public UserDTO(){
    }
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.profilePicUrl = user.getProfilePicUrl();
        this.roles = user.getRoles();
    }
    public UserDTO(Integer id, String name, String username, String profilePicUrl, List<String> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.roles = roles;
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
}
