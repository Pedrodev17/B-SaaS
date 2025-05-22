package org.example.bsaas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Assuming User, Contact, and PipelineStage entities are in this package or imported
// import org.example.bsaas.Entities.User;
// import org.example.bsaas.Entities.Contact;
// import org.example.bsaas.Entities.PipelineStage;

@Entity
@Table(name = "Deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealID")
    private Integer dealId;

    @Column(name = "DealName", nullable = false, length = 150)
    private String dealName;

    @Column(name = "Amount", precision = 12, scale = 2) // Matches DECIMAL(12, 2)
    private BigDecimal amount;

    @Column(name = "Currency", length = 3)
    private String currency; // e.g., "USD"

    @Temporal(TemporalType.DATE) // For DATE type columns
    @Column(name = "ExpectedCloseDate")
    private Date expectedCloseDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "ActualCloseDate")
    private Date actualCloseDate;

    @Column(name = "Probability", precision = 5, scale = 2) // Matches DECIMAL(5,2)
    private BigDecimal probability;

    @Lob // For potentially large text fields
    @Column(name = "Notes", columnDefinition = "TEXT")
    private String notes;

    // Foreign Key to Primary Contact
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PrimaryContactID", referencedColumnName = "ContactID")
    private Contact primaryContact;

    // Foreign Key to Assigned User (Sales Representative)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AssignedUserID", referencedColumnName = "UserID")
    private User assignedUser;

    // Foreign Key to Current Pipeline Stage
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CurrentStageID", referencedColumnName = "StageID")
    private PipelineStage currentStage;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    // Relationship to DealContacts (One Deal to Many DealContacts)
    // This assumes you have a DealContact entity to manage the many-to-many relationship
    // with the extra 'RoleInDeal' column.
    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<DealContact> dealContacts = new HashSet<>();

    // Relationship to DealCollaborators (One Deal to Many DealCollaborators)
    // This assumes you have a DealCollaborator entity.
    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<DealCollaborator> dealCollaborators = new HashSet<>();


    // Constructors
    public Deal() {
    }

    // Getters and Setters
    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getExpectedCloseDate() {
        return expectedCloseDate;
    }

    public void setExpectedCloseDate(Date expectedCloseDate) {
        this.expectedCloseDate = expectedCloseDate;
    }

    public Date getActualCloseDate() {
        return actualCloseDate;
    }

    public void setActualCloseDate(Date actualCloseDate) {
        this.actualCloseDate = actualCloseDate;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Contact getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Contact primaryContact) {
        this.primaryContact = primaryContact;
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

    public Set<DealContact> getDealContacts() {
        return dealContacts;
    }

    public void setDealContacts(Set<DealContact> dealContacts) {
        this.dealContacts = dealContacts;
    }

    public Set<DealCollaborator> getDealCollaborators() {
        return dealCollaborators;
    }

    public void setDealCollaborators(Set<DealCollaborator> dealCollaborators) {
        this.dealCollaborators = dealCollaborators;
    }

    // Helper methods for managing bi-directional relationships (if needed for DealContacts/DealCollaborators)
    public void addDealContact(DealContact dealContact) {
        dealContacts.add(dealContact);
        dealContact.setDeal(this);
    }

    public void removeDealContact(DealContact dealContact) {
        dealContacts.remove(dealContact);
        dealContact.setDeal(null);
    }

    public void addDealCollaborator(DealCollaborator dealCollaborator) {
        dealCollaborators.add(dealCollaborator);
        dealCollaborator.setDeal(this);
    }

    public void removeDealCollaborator(DealCollaborator dealCollaborator) {
        dealCollaborators.remove(dealCollaborator);
        dealCollaborator.setDeal(null);
    }
// Consider overriding equals(), hashCode(), and toString()
}