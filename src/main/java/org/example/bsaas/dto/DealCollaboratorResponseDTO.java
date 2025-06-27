package org.example.bsaas.dto;

public class DealCollaboratorResponseDTO {

    private Integer id;
    private Integer dealId;
    private Integer userId;
    private String userName; // exemplo de campo extra

    // getters e setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getDealId() { return dealId; }
    public void setDealId(Integer dealId) { this.dealId = dealId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}