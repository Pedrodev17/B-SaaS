package org.example.bsaas.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.Contact;

@Entity
@Table(name = "DealContacts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"DealID", "ContactID"}))
public class DealContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DealContactID")
    private Integer dealContactId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DealID", nullable = false)
    private Deal deal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ContactID", nullable = false)
    private Contact contact;

    @Column(name = "RoleInDeal", length = 50)
    private String roleInDeal;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Constructors
    public DealContact() {
    }

    public DealContact(Deal deal, Contact contact, String roleInDeal) {
        this.deal = deal;
        this.contact = contact;
        this.roleInDeal = roleInDeal;
    }

    // Getters and Setters
    public Integer getDealContactId() {
        return dealContactId;
    }

    public void setDealContactId(Integer dealContactId) {
        this.dealContactId = dealContactId;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    // It's good practice to also override equals() and hashCode()
    // especially if these objects are used in Sets or as keys in Maps.
    // For brevity, they are omitted here.

    @Override
    public String toString() {
        return "DealContact{" +
                "dealContactId=" + dealContactId +
                ", dealId=" + (deal != null ? deal.getDealId() : "null") + // Avoid NullPointerException
                ", contactId=" + (contact != null ? contact.getContactId() : "null") + // Avoid NullPointerException
                ", roleInDeal='" + roleInDeal + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}