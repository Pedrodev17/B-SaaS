package org.example.bsaas.repository;

import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.model.Deal; // For query method
import org.example.bsaas.model.User;  // For query method
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealCollaboratorRepository extends JpaRepository<DealCollaborator, Integer> {

    /**
     * Finds all collaborators for a specific deal.
     * @param deal The deal entity to find collaborators for.
     * @return A list of DealCollaborator entries for the given deal.
     */
    List<DealCollaborator> findByDeal(Deal deal);

    /**
     * Finds all deal collaborations for a specific user.
     * @param user The user entity to find collaborations for.
     * @return A list of DealCollaborator entries for the given user.
     */
    List<DealCollaborator> findByUser(User user);

    /**
     * Finds a specific collaboration entry by Deal and User.
     * This can be useful to check if a user is already a collaborator on a deal.
     * @param deal The deal entity.
     * @param user The user entity.
     * @return An Optional containing the DealCollaborator if found.
     */
    List<DealCollaborator> findByDealAndUser(Deal deal, User user);

    /**
     * Finds all collaborators for a specific deal ID.
     * @param dealId The ID of the deal.
     * @return A list of DealCollaborator entries for the given deal ID.
     */
    List<DealCollaborator> findByDealDealId(Integer dealId);

    /**
     * Finds all deal collaborations for a specific user ID.
     * @param userId The ID of the user.
     * @return A list of DealCollaborator entries for the given user ID.
     */
    List<DealCollaborator> findByUserUserId(Integer userId);
}