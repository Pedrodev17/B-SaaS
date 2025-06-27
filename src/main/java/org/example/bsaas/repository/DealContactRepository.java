package org.example.bsaas.repository;

import org.example.bsaas.model.DealContact;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DealContactRepository extends JpaRepository<DealContact, Integer> {

    List<DealContact> findByDeal(Deal deal);

    List<DealContact> findByContact(Contact contact);

    Optional<DealContact> findByDealAndContact(Deal deal, Contact contact);

    List<DealContact> findByDealDealId(Integer dealId);

    List<DealContact> findByContactContactId(Integer contactId);

    // Métodos opcionais para paginação:
    Page<DealContact> findByDeal(Deal deal, Pageable pageable);

    Page<DealContact> findByContact(Contact contact, Pageable pageable);
}