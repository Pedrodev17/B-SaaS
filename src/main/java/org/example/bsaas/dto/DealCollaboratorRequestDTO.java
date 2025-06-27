package org.example.bsaas.dto;

import jakarta.validation.constraints.NotNull;

public class DealCollaboratorRequestDTO {

    @NotNull
    private Integer dealId;

    @NotNull
    private Integer userId;

    // outros campos, se necessários

    public Integer getDealId() { return dealId; }
    public void setDealId(Integer dealId) { this.dealId = dealId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}