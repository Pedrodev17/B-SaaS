package org.example.bsaas.service;

import org.example.bsaas.dto.DealCollaboratorRequestDTO;
import org.example.bsaas.dto.DealCollaboratorResponseDTO;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.model.DealCollaborator;

public class DealCollaboratorMapper {

    public static DealCollaborator toEntity(DealCollaboratorRequestDTO dto, Deal deal, User user) {
        DealCollaborator entity = new DealCollaborator();
        entity.setDeal(deal);
        entity.setUser(user);
        // Se quiser lidar com roleInDeal, acrescente aqui (não está no DTO atualmente)
        return entity;
    }

    public static DealCollaboratorResponseDTO toDTO(DealCollaborator entity) {
        DealCollaboratorResponseDTO dto = new DealCollaboratorResponseDTO();
        dto.setId(entity.getDealCollaboratorId());
        dto.setDealId(entity.getDeal() != null ? entity.getDeal().getDealId() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : null);
        if (entity.getUser() != null) {
            dto.setUserName(entity.getUser().getName());
        }
        return dto;
    }
}