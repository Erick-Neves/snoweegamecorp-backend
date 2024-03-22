package com.snoweegamecorp.api.repository;

import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;
    private User user;
    @BeforeEach
    public void init(){
        user = TestUtils.instantiateNewUser(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }
    @Test
    public void UserRepository_SaveUser_ReturnUser(){
        User savedUser = repository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
    }
    @Test
    public void UserRepository_FindUserByUsername_ReturnOneUser(){
        User userToFind = repository.save(user);
        User foundUser = repository.findByUsername(userToFind.getUsername());
        Assertions.assertThat(foundUser).isNotNull();
    }
    @Test
    public void UserRepository_FindUserById_ReturnOneUser(){
        User userToFind = repository.save(user);
        User foundUser = repository.findById(userToFind.getId()).get();
        Assertions.assertThat(foundUser).isNotNull();
    }
    @Test
    public void UserRepository_FindAllUsers_ReturnListUsers(){
        repository.save(user);
        repository.save(TestUtils.instantiateNewUser(2));
        List<User> users = repository.findAll();
        Assertions.assertThat(users.stream().count() == 2);
    }
    @Test
    public void UserRepository_UpdateUser_ReturnUpdatedUser() {
        user = repository.save(user);
        User userToUpdate = repository.findById(user.getId()).get();
        userToUpdate.setName("Another Tester");
        userToUpdate.setUsername("anothertester@gmail.com");
        User updatedUser = repository.save(userToUpdate);
        Assertions.assertThat(updatedUser.getName().equals("Another Tester"));
    }
    @Test
    public void UserRepository_DeleteUser_ReturnOptional(){
        User userToDelete = repository.save(user);
        repository.save(userToDelete);
        repository.deleteById(userToDelete.getId());
        Optional<User> foundUser = repository.findById(userToDelete.getId());
        Assertions.assertThat(foundUser.isEmpty());
    }
}
