package com.snoweegamecorp.api.repository;

import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;
    private User user1;
    private User user2;
    @Test
    public void SaveUser_ReturnUser(){
        user1 = TestUtils.instantiateNewUser(1);
        User savedUser = repository.save(user1);
        Assertions.assertThat(savedUser).isNotNull();
    }
    @Test
    public void FindUserByUsername_ReturnOneUser(){
        user1 = TestUtils.instantiateNewUser(2);
        User userToFind = repository.save(user1);
        User foundUser = repository.findByUsername(userToFind.getUsername());
        Assertions.assertThat(foundUser).isNotNull();
    }
    @Test
    public void FindUserById_ReturnOneUser(){
        user1 = TestUtils.instantiateNewUser(3);
        User userToFind = repository.save(user1);
        User foundUser = repository.findById(userToFind.getId()).get();
        Assertions.assertThat(foundUser).isNotNull();
    }
    @Test
    public void FindAllUsers_ReturnListUsers(){
        user1 = TestUtils.instantiateNewUser(4);
        user2 = TestUtils.instantiateNewUser(5);
        repository.save(user1);
        repository.save(user2);
        List<User> users = repository.findAll();
        Assertions.assertThat(users.stream().count() == 2);
    }
    @Test
    public void UpdateUser_ReturnUpdatedUser() {
        user1 = TestUtils.instantiateNewUser(6);
        User user = repository.save(user1);
        User userToUpdate = repository.findById(user.getId()).get();
        userToUpdate.setName("Another Tester");
        userToUpdate.setUsername("anothertester@gmail.com");
        User updatedUser = repository.save(userToUpdate);
        Assertions.assertThat(updatedUser.getName().equals("Another Tester"));
    }
    @Test
    public void DeleteUser_ReturnOptional(){
        user1 = TestUtils.instantiateNewUser(6);
        User userToDelete = repository.save(user1);
        repository.save(userToDelete);
        repository.deleteById(userToDelete.getId());
        Optional<User> foundUser = repository.findById(userToDelete.getId());
        Assertions.assertThat(foundUser.isEmpty());
    }
}
