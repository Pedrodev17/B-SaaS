package org.example.bsaas.repository;

import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealCollaboratorRepository extends JpaRepository<DealCollaborator, Integer> {

    /**
     * Retorna todos os colaboradores de um negócio.
     */
    List<DealCollaborator> findByDeal(Deal deal);

    /**
     * Retorna todas as colaborações de um usuário.
     */
    List<DealCollaborator> findByUser(User user);

    /**
     * Busca uma colaboração específica entre um negócio e um usuário.
     */
    Optional<DealCollaborator> findByDealAndUser(Deal deal, User user);

    /**
     * Retorna todos os colaboradores de um negócio pelo ID do negócio.
     */
    List<DealCollaborator> findByDealDealId(Integer dealId);

    /**
     * Retorna todas as colaborações de um usuário pelo ID do usuário.
     */
    List<DealCollaborator> findByUserUserId(Integer userId);
}