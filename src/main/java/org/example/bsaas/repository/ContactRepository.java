package org.example.bsaas.repository;

import org.example.bsaas.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<Contact> findByEmail(String email);

    List<Contact> findByOwnerUserUserId(Integer ownerUserId);

    List<Contact> findByFirstNameContainingIgnoreCase(String firstName);

    List<Contact> findByLastNameContainingIgnoreCase(String lastName);

    List<Contact> findByCompanyNameIgnoreCase(String companyName);

    // Exemplos opcionais de métodos paginados:
    Page<Contact> findByCompanyNameIgnoreCase(String companyName, Pageable pageable);

    Page<Contact> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}