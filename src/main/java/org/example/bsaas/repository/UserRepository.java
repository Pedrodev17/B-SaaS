package org.example.bsaas.repository;

import org.example.bsaas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their username.
     * @param username The username to search for.
     * @return An Optional containing the user if found, or an empty Optional otherwise.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email address.
     * @param email The email address to search for.
     * @return An Optional containing the user if found, or an empty Optional otherwise.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds users by their role.
     * @param role The role to search for.
     * @return A list of users with the specified role.
     */
    List<User> findByRole(String role);

    /**
     * Finds active users.
     * @return A list of active users.
     */
    List<User> findByIsActiveTrue();

    /**
     * Finds inactive users.
     * @return A list of inactive users.
     */
    List<User> findByIsActiveFalse();
}