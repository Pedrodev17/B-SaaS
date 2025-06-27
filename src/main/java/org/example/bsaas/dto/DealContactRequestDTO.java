package org.example.bsaas.dto;

import jakarta.validation.constraints.NotNull;

public class DealContactRequestDTO {

    @NotNull
    private Integer dealId;

    @NotNull
    private Integer contactId;

    // outros campos, se necessários

    public Integer getDealId() { return dealId; }
    public void setDealId(Integer dealId) { this.dealId = dealId; }

    public Integer getContactId() { return contactId; }
    public void setContactId(Integer contactId) { this.contactId = contactId; }
}