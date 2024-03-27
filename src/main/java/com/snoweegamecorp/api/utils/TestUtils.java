package com.snoweegamecorp.api.utils;

import com.snoweegamecorp.api.dto.LoginDTO;
import com.snoweegamecorp.api.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains utility methods for testing purposes.
 */
public class TestUtils {
    /**
     * Instantiate a new user with the provided id.
     *
     * @param id the id of the user
     * @return the newly instantiated user
     */
    public static User instantiateNewUser(Integer id){
        // Create a list of roles and add a default role
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        // Build and return the new user object with default values
        User user = User.builder()
                .id(id)
                .name("Tester")
                .username("tester"+id+"@gmail.com")
                .password("123456")
                .profilePicUrl("https://i.imgur.com/LGGL7VJ.png")
                .roles(roles)
                .build();
        return user;
    }
}
