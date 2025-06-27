package org.example.bsaas.service;

import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.DealRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável pelas operações de negócio relacionadas à entidade Deal.
 */
@Service
public class DealService {

    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    public DealService(DealRepository dealRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retorna todos os negócios cadastrados.
     */
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    /**
     * Busca um negócio pelo id.
     * @throws ResourceNotFoundException se não existir
     */
    public Deal getDealById(Integer id) {
        return dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negócio não encontrado com id: " + id));
    }

    /**
     * Cria um novo negócio. Valida campos obrigatórios e o proprietário.
     */
    @Transactional
    public Deal createDeal(Deal deal) {
        validarCamposObrigatorios(deal);
        setAndValidateOwner(deal);
        return dealRepository.save(deal);
    }

    /**
     * Atualiza um negócio existente.
     * @param id id do negócio
     * @param dealDetails detalhes atualizados
     * @return negócio atualizado
     * @throws ResourceNotFoundException se não existir
     */
    @Transactional
    public Deal updateDeal(Integer id, Deal dealDetails) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negócio não encontrado com id: " + id));
        validarCamposObrigatorios(dealDetails);
        updateDealFields(deal, dealDetails);
        setAndValidateOwner(dealDetails);
        deal.setOwnerUser(dealDetails.getOwnerUser());
        return dealRepository.save(deal);
    }

    /**
     * Exclui um negócio pelo id.
     * @throws ResourceNotFoundException se não existir
     */
    @Transactional
    public void deleteDeal(Integer id) {
        if (!dealRepository.existsById(id)) {
            throw new ResourceNotFoundException("Negócio não encontrado com id: " + id);
        }
        dealRepository.deleteById(id);
    }

    /**
     * Valida e define o usuário proprietário do negócio.
     * @param deal negócio a ser validado
     */
    private void setAndValidateOwner(Deal deal) {
        if (deal.getOwnerUser() != null && deal.getOwnerUser().getUserId() != null) {
            User owner = userRepository.findById(deal.getOwnerUser().getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + deal.getOwnerUser().getUserId()));
            deal.setOwnerUser(owner);
        }
    }

    /**
     * Atualiza campos do negócio caso não sejam nulos.
     * Permite atualização parcial.
     * @param deal negócio existente
     * @param details dados atualizados
     */
    private void updateDealFields(Deal deal, Deal details) {
        if (details.getTitle() != null) {
            deal.setTitle(details.getTitle());
        }
        if (details.getDescription() != null) {
            deal.setDescription(details.getDescription());
        }
        if (details.getValue() != null) {
            deal.setValue(details.getValue());
        }
        if (details.getStatus() != null) {
            deal.setStatus(details.getStatus());
        }
        if (details.getCloseDate() != null) {
            deal.setCloseDate(details.getCloseDate());
        }
    }

    /**
     * Valida campos obrigatórios do negócio.
     * @param deal negócio a ser validado
     * @throws IllegalArgumentException se algum campo obrigatório estiver ausente
     */
    private void validarCamposObrigatorios(Deal deal) {
        if (deal.getTitle() == null || deal.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (deal.getValue() == null) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }
        // Adicione outras validações conforme necessário
    }
}