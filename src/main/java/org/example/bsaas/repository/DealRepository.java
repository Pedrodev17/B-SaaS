package org.example.bsaas.repository;

import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.PipelineStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findByDealNameContainingIgnoreCase(String dealName);
    Page<Deal> findByDealNameContainingIgnoreCase(String dealName, Pageable pageable);

    List<Deal> findByAssignedUser(User assignedUser);
    List<Deal> findByAssignedUserUserId(Integer userId);

    List<Deal> findByPrimaryContact(Contact primaryContact);
    List<Deal> findByPrimaryContactContactId(Integer contactId);

    List<Deal> findByCurrentStage(PipelineStage currentStage);
    List<Deal> findByCurrentStageStageId(Integer stageId);

    List<Deal> findByExpectedCloseDateBefore(Date date);
    List<Deal> findByExpectedCloseDateAfter(Date date);

    List<Deal> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);

    List<Deal> findByCurrency(String currency);

    @Query("SELECT d FROM Deal d WHERE d.probability > :probability")
    List<Deal> findDealsWithHighProbability(@Param("probability") BigDecimal probability);

    // Exemplos opcionais com paginação
    Page<Deal> findByAssignedUser(User assignedUser, Pageable pageable);
    Page<Deal> findByCurrency(String currency, Pageable pageable);
}