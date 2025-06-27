package org.example.bsaas.repository;

import org.example.bsaas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByRole(String role);

    List<User> findByIsActiveTrue();

    List<User> findByIsActiveFalse();

    // Opcional: métodos paginados
    Page<User> findByRole(String role, Pageable pageable);
    Page<User> findByIsActiveTrue(Pageable pageable);
    Page<User> findByIsActiveFalse(Pageable pageable);

    // Opcional: busca combinada
    List<User> findByRoleAndIsActiveTrue(String role);
}