package org.example.bsaas.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

// Assuming Deal and User entities are in this package or imported
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;

@Entity
@Table(name = "DealCollaborators",
        uniqueConstraints = @UniqueConstraint(columnNames = {"DealID", "UserID"}))
public class DealCollaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealCollaboratorID")
    private Integer dealCollaboratorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealID", nullable = false)
    private Deal deal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user; // This links to the User entity

    @Column(name = "RoleInDeal", length = 50)
    private String roleInDeal;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Constructors
    public DealCollaborator() {
    }

    public DealCollaborator(Deal deal, User user, String roleInDeal) {
        this.deal = deal;
        this.user = user;
        this.roleInDeal = roleInDeal;
    }

    // Getters and Setters
    public Integer getDealCollaboratorId() {
        return dealCollaboratorId;
    }

    public void setDealCollaboratorId(Integer dealCollaboratorId) {
        this.dealCollaboratorId = dealCollaboratorId;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleInDeal() {
        return roleInDeal;
    }

    public void setRoleInDeal(String roleInDeal) {
        this.roleInDeal = roleInDeal;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealCollaborator that = (DealCollaborator) o;
        return java.util.Objects.equals(dealCollaboratorId, that.dealCollaboratorId) &&
               java.util.Objects.equals(deal, that.deal) &&
               java.util.Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(dealCollaboratorId, deal, user);
    }

    @Override
    public String toString() {
        return "DealCollaborator{" +
                "dealCollaboratorId=" + dealCollaboratorId +
                ", dealId=" + (deal != null ? deal.getDealId() : "null") +
                ", userId=" + (user != null ? user.getUserId() : "null") + // Assuming User entity has getUserId()
                ", roleInDeal='" + roleInDeal + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}