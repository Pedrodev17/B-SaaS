package org.example.bsaas.service;

import org.example.bsaas.dto.DealContactRequestDTO;
import org.example.bsaas.dto.DealContactResponseDTO;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.DealContact;

public class DealContactMapper {

    public static DealContact toEntity(DealContactRequestDTO dto, Deal deal, Contact contact) {
        DealContact entity = new DealContact();
        entity.setDeal(deal);
        entity.setContact(contact);
        // Se quiser lidar com roleInDeal, acrescente aqui (não está no DTO atualmente)
        return entity;
    }

    public static DealContactResponseDTO toDTO(DealContact entity) {
        DealContactResponseDTO dto = new DealContactResponseDTO();
        dto.setId(entity.getDealContactId());
        dto.setDealId(entity.getDeal() != null ? entity.getDeal().getDealId() : null);
        dto.setContactId(entity.getContact() != null ? entity.getContact().getContactId() : null);
        if (entity.getContact() != null) {
            String name =
                    (entity.getContact().getFirstName() != null ? entity.getContact().getFirstName() : "") +
                            (entity.getContact().getLastName() != null && !entity.getContact().getLastName().isBlank()
                                    ? " " + entity.getContact().getLastName()
                                    : "");
            dto.setContactName(name.trim());
        }
        return dto;
    }
}