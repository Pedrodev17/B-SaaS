package org.example.bsaas.repository;

import org.example.bsaas.model.DealContact;
import org.example.bsaas.model.Deal;    // For query method
import org.example.bsaas.model.Contact; // For query method
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealContactRepository extends JpaRepository<DealContact, Integer> {

    /**
     * Finds all contacts associated with a specific deal.
     * @param deal The deal entity.
     * @return A list of DealContact entries for the given deal.
     */
    List<DealContact> findByDeal(Deal deal);

    /**
     * Finds all deals a specific contact is associated with.
     * @param contact The contact entity.
     * @return A list of DealContact entries for the given contact.
     */
    List<DealContact> findByContact(Contact contact);

    /**
     * Finds a specific deal-contact association.
     * @param deal The deal entity.
     * @param contact The contact entity.
     * @return An Optional containing the DealContact if found.
     */
    Optional<DealContact> findByDealAndContact(Deal deal, Contact contact);

    /**
     * Finds all contacts for a specific deal ID.
     * @param dealId The ID of the deal.
     * @return A list of DealContact entries for the given deal ID.
     */
    List<DealContact> findByDealDealId(Integer dealId);

    /**
     * Finds all deals a specific contact ID is associated with.
     * @param contactId The ID of the contact.
     * @return A list of DealContact entries for the given contact ID.
     */
    List<DealContact> findByContactContactId(Integer contactId);
}