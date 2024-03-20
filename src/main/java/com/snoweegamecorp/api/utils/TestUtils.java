package com.snoweegamecorp.api.utils;

import com.snoweegamecorp.api.model.User;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static User instantiateNewUser(Integer id){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = User.builder()
                .id(id)
                .name("Tester")
                .username("tester"+id+"@gmail.com")
                .password("123456")
                .profilePicUrl("")
                .roles(roles)
                .build();
        return user;
    }
}
