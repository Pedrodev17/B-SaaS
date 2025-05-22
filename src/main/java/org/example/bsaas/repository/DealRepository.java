package org.example.bsaas.repository;

import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.PipelineStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {

    /**
     * Finds deals by their name, case-insensitive.
     * @param dealName The name of the deal to search for.
     * @return A list of deals matching the name.
     */
    List<Deal> findByDealNameContainingIgnoreCase(String dealName);

    /**
     * Finds deals assigned to a specific user.
     * @param assignedUser The user to whom deals are assigned.
     * @return A list of deals assigned to the user.
     */
    List<Deal> findByAssignedUser(User assignedUser);
    List<Deal> findByAssignedUserUserId(Integer userId);


    /**
     * Finds deals associated with a primary contact.
     * @param primaryContact The primary contact for the deals.
     * @return A list of deals for the primary contact.
     */
    List<Deal> findByPrimaryContact(Contact primaryContact);
    List<Deal> findByPrimaryContactContactId(Integer contactId);

    /**
     * Finds deals currently in a specific pipeline stage.
     * @param currentStage The pipeline stage.
     * @return A list of deals in the specified stage.
     */
    List<Deal> findByCurrentStage(PipelineStage currentStage);
    List<Deal> findByCurrentStageStageId(Integer stageId);


    /**
     * Finds deals with an expected close date before a certain date.
     * @param date The cut-off date.
     * @return A list of deals expected to close before the date.
     */
    List<Deal> findByExpectedCloseDateBefore(Date date);

    /**
     * Finds deals with an expected close date after a certain date.
     * @param date The cut-off date.
     * @return A list of deals expected to close after the date.
     */
    List<Deal> findByExpectedCloseDateAfter(Date date);

    /**
     * Finds deals within a specific amount range.
     * @param minAmount The minimum amount.
     * @param maxAmount The maximum amount.
     * @return A list of deals within the amount range.
     */
    List<Deal> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);

    /**
     * Finds deals by currency.
     * @param currency The currency code (e.g., "USD").
     * @return A list of deals with the specified currency.
     */
    List<Deal> findByCurrency(String currency);

    /**
     * Example of a custom query using @Query annotation to find deals
     * with a probability greater than a specified value.
     * @param probability The minimum probability.
     * @return A list of deals.
     */
    @Query("SELECT d FROM Deal d WHERE d.probability > :probability")
    List<Deal> findDealsWithHighProbability(@Param("probability") BigDecimal probability);
}