package org.example.bsaas.repository;

import org.example.bsaas.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Spring Data JPA will automatically generate the query for these methods based on their names.

    /**
     * Finds a contact by their email address.
     * @param email The email address to search for.
     * @return An Optional containing the contact if found, or an empty Optional otherwise.
     */
    Optional<Contact> findByEmail(String email);

    /**
     * Finds all contacts associated with a specific owner user ID.
     * @param ownerUserId The ID of the owner user.
     * @return A list of contacts owned by the specified user.
     */
    List<Contact> findByOwnerUserUserId(Integer ownerUserId); // Assumes User entity has a field 'userId'

    /**
     * Finds contacts whose first name contains the given string, case-insensitive.
     * @param firstName The string to search for in the first name.
     * @return A list of matching contacts.
     */
    List<Contact> findByFirstNameContainingIgnoreCase(String firstName);

    /**
     * Finds contacts whose last name contains the given string, case-insensitive.
     * @param lastName The string to search for in the last name.
     * @return A list of matching contacts.
     */
    List<Contact> findByLastNameContainingIgnoreCase(String lastName);

    /**
     * Finds contacts by company name, case-insensitive.
     * @param companyName The company name to search for.
     * @return A list of contacts belonging to the specified company.
     */
    List<Contact> findByCompanyNameIgnoreCase(String companyName);

}