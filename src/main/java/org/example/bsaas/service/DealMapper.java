package org.example.bsaas.service;

import org.example.bsaas.dto.DealRequestDTO;
import org.example.bsaas.dto.DealResponseDTO;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;

public class DealMapper {

    public static Deal toEntity(DealRequestDTO dto, User ownerUser) {
        Deal deal = new Deal();
        deal.setTitle(dto.getTitle());
        deal.setDescription(dto.getDescription());
        deal.setValue(dto.getAmount());
        deal.setOwnerUser(ownerUser);
        // Outros campos podem ser preenchidos conforme necessidade
        return deal;
    }

    public static DealResponseDTO toDTO(Deal deal) {
        DealResponseDTO dto = new DealResponseDTO();
        dto.setDealId(deal.getDealId());
        dto.setTitle(deal.getTitle());
        dto.setDescription(deal.getDescription());
        dto.setAmount(deal.getValue());
        if (deal.getOwnerUser() != null) {
            dto.setOwnerUserId(deal.getOwnerUser().getUserId());
            dto.setOwnerUserName(deal.getOwnerUser().getName());
        }
        return dto;
    }
}