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

    @Column(name = "Value", precision = 15, scale = 2)
    private BigDecimal value;

    @Column(name = "Status", length = 50)
    private String status;

    @Column(name = "CloseDate")
    private Timestamp closeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerUserID", referencedColumnName = "UserID")
    private User ownerUser;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    public long getTitle(Object description) {
        return title != null ? title.length() : 0;
    }

    public Object getDescription() {
        return description != null ? description.length() : 0;
    }

    public Deal getValue() {
        return new Deal();
    }

    // Getters and setters...
}