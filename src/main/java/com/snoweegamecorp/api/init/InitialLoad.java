package com.snoweegamecorp.api.init;

import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialLoad implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;
    @Value("${env.user.admin.username}")
    private String adminUsername;
    @Value("${env.user.admin.password}")
    private String adminPassword;
    @Value("${env.user.tester.username}")
    private String testerUsername;
    @Value("${env.user.tester.password}")
    private String testerPassword;
    @Value("${env.user.guest.username}")
    private String guestUsername;
    @Value("${env.user.guest.password}")
    private String guestPassword;
    public String profilePicAdmin = "https://i.imgur.com/CWf3Y4j.jpg";
    public String profilePicUsers = "https://i.imgur.com/LGGL7VJ.png";
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername(adminUsername);
        if(user==null){
            user = new User();
            user.setName("Admin");
            user.setUsername(adminUsername);
            user.setPassword(adminPassword);
            user.getRoles().add("MANAGERS");
            user.getRoles().add("USERS");
            user.setProfilePicUrl(profilePicAdmin);
            service.createUser(user);
        }
        user = repository.findByUsername(testerUsername);
        if(user==null){
            user = new User();
            user.setName("Tester");
            user.setUsername(testerUsername);
            user.setPassword(testerPassword);
            user.getRoles().add("MANAGERS");
            user.getRoles().add("USERS");
            user.setProfilePicUrl(profilePicUsers);
            service.createUser(user);
        }
        user = repository.findByUsername(guestUsername);
        if(user==null){
            user = new User();
            user.setName("User");
            user.setUsername(guestUsername);
            user.setPassword(guestPassword);
            user.getRoles().add("USERS");
            user.setProfilePicUrl(profilePicUsers);
            service.createUser(user);
        }
    }
}
