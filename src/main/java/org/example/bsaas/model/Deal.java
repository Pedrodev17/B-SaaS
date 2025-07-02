package org.example.bsaas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealID")
    private Integer dealId;

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Column(name = "Description", length = 1000)
    private String description;

    @Column(name = "DealValue", precision = 15, scale = 2)
    private BigDecimal value;

    @Column(name = "Probability", precision = 5, scale = 2)
    private BigDecimal probability;

    @Column(name = "Currency", length = 10)
    private String currency;

    @Column(name = "Status", length = 50)
    private String status;

    @Column(name = "CloseDate")
    private Timestamp closeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerUserID", referencedColumnName = "UserID")
    private User ownerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AssignedUserID", referencedColumnName = "UserID")
    private User assignedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CurrentStageID", referencedColumnName = "StageID")
    private PipelineStage currentStage;

    // ADICIONADO: Relacionamento com o contato principal (caso deseje buscar por contato principal)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PrimaryContactID", referencedColumnName = "ContactID")
    private Contact primaryContact;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    public Deal() {}

    public Deal(String title, String description, BigDecimal value, BigDecimal probability, String currency, String status,
                Timestamp closeDate, User ownerUser, User assignedUser, PipelineStage currentStage, Contact primaryContact) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.probability = probability;
        this.currency = currency;
        this.status = status;
        this.closeDate = closeDate;
        this.ownerUser = ownerUser;
        this.assignedUser = assignedUser;
        this.currentStage = currentStage;
        this.primaryContact = primaryContact;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public PipelineStage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(PipelineStage currentStage) {
        this.currentStage = currentStage;
    }

    public Contact getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Contact primaryContact) {
        this.primaryContact = primaryContact;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deal deal)) return false;
        return dealId != null && dealId.equals(deal.dealId);
    }

    @Override
    public int hashCode() {
        return dealId != null ? dealId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "dealId=" + dealId +
                ", title='" + title + '\'' +
                ", value=" + value +
                ", probability=" + probability +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", closeDate=" + closeDate +
                ", ownerUser=" + (ownerUser != null ? ownerUser.getUserId() : null) +
                ", assignedUser=" + (assignedUser != null ? assignedUser.getUserId() : null) +
                ", currentStage=" + (currentStage != null ? currentStage.getStageId() : null) +
                ", primaryContact=" + (primaryContact != null ? primaryContact.getContactId() : null) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}