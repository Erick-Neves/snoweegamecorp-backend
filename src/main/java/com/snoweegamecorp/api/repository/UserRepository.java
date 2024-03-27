package com.snoweegamecorp.api.repository;

import com.snoweegamecorp.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for accessing the User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user by their username, eagerly fetching their roles.
     *
     * @param username the username to search for
     * @return the user with the given username, or null if not found
     */
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username=(:username)")
    public User findByUsername(@Param("username") String username);
    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);
}