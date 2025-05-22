package org.example.bsaas.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash; // Remember to handle hashing securely

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "Role", length = 50)
    private String role; // Consider using an Enum for roles

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    public String getUserId() {
        return "";
    }

    // Getters and Setters
    // Constructors
    // hashCode, equals, toString (optional but good practice)
}