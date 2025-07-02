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
import java.sql.Timestamp;
import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findByTitleContainingIgnoreCase(String title);
    Page<Deal> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Deal> findByAssignedUser(User assignedUser);
    List<Deal> findByAssignedUserUserId(Integer userId);

    // Métodos para buscar pelo contato principal (agora existente em Deal)
    List<Deal> findByPrimaryContact(Contact primaryContact);
    List<Deal> findByPrimaryContactContactId(Integer contactId);

    List<Deal> findByCurrentStage(PipelineStage currentStage);
    List<Deal> findByCurrentStageStageId(Integer stageId);

    List<Deal> findByCloseDateBefore(Timestamp date);
    List<Deal> findByCloseDateAfter(Timestamp date);

    List<Deal> findByValueBetween(BigDecimal minValue, BigDecimal maxValue);

    List<Deal> findByCurrency(String currency);

    @Query("SELECT d FROM Deal d WHERE d.probability > :probability")
    List<Deal> findDealsWithHighProbability(@Param("probability") BigDecimal probability);

    // Exemplos opcionais com paginação
    Page<Deal> findByAssignedUser(User assignedUser, Pageable pageable);
    Page<Deal> findByCurrency(String currency, Pageable pageable);
}