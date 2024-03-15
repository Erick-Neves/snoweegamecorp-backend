package com.snoweegamecorp.api.repository;

import com.snoweegamecorp.api.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;
    @Test
    public void SaveUser(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                1,
                "Tester",
                "tester1@gmail.com",
                "123456",
                "",
                roles );
        User savedUser = repository.save(user);
        Assertions.assertNotNull(savedUser);
    }
    @Test
    public void FindUserByUsername() throws InterruptedException {
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                2,
                "Tester",
                "tester2@gmail.com",
                "123456",
                "",
                roles );
        repository.save(user);
        User foundUser = repository.findByUsername(user.getUsername());
        Assertions.assertNotNull(foundUser);
    }
    @Test
    public void FindUserById(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                3,
                "Tester",
                "tester3@gmail.com",
                "123456",
                "",
                roles );
        repository.save(user);
        User foundUser = repository.findById(3).get();
        Assertions.assertNotNull(foundUser);
    }
    @Test
    public void FindAllUsers(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user1 = new User(
                4,
                "Tester",
                "tester4@gmail.com",
                "123456",
                "",
                roles );
        User user2 = new User(
                5,
                "Tester",
                "tester5@gmail.com",
                "123456",
                "",
                roles );
        repository.save(user1);
        repository.save(user2);
        List<User> users = repository.findAll();
        Assertions.assertTrue(users.stream().count() == 2);
    }
    @Test
    public void UpdateUser() {
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                6,
                "Tester",
                "tester6@gmail.com",
                "123456",
                "",
                roles );
        repository.save(user);
        System.out.println(repository.save(user));
        User foundUser = repository.findById(6).get();
        foundUser.setName("Another Tester");
        foundUser.setUsername("anothertester@gmail.com");
        User updatedUser = repository.save(foundUser);
        Assertions.assertEquals(updatedUser.getName(), "Another Tester");
    }
    @Test
    public void DeleteUser(){
        List<String> roles = new ArrayList<>();
        roles.add("USERS");
        User user = new User(
                1,
                "Tester",
                "tester@gmail.com",
                "123456",
                "",
                roles );
        repository.save(user);
        repository.deleteById(user.getId());
        Optional<User> foundUser = repository.findById(1);
        Assertions.assertTrue(foundUser.isEmpty());
    }
}
