package org.example.bsaas.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
// Assuming you have a User entity defined in the same package or imported


@Entity
@Table(name = "Contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactID")
    private Integer contactId;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "Email", unique = true, length = 100)
    private String email;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "CompanyName", length = 100)
    private String companyName;

    @Column(name = "JobTitle", length = 100)
    private String jobTitle;

    @Column(name = "AddressStreet", length = 255)
    private String addressStreet;

    @Column(name = "AddressCity", length = 100)
    private String addressCity;

    @Column(name = "AddressState", length = 100)
    private String addressState;

    @Column(name = "AddressZipCode", length = 20)
    private String addressZipCode;

    @Column(name = "AddressCountry", length = 100)
    private String addressCountry;

    @Column(name = "LeadSource", length = 50)
    private String leadSource;

    @Lob // For potentially large text fields, maps to TEXT or CLOB
    @Column(name = "Notes", columnDefinition = "TEXT")
    private String notes;

    // Foreign Key to User (Owner of the contact)
    // The JoinColumn name should match the foreign key column in the Contacts table
    @ManyToOne(fetch = FetchType.LAZY) // LAZY fetching is generally recommended for performance
    @JoinColumn(name = "OwnerUserID", referencedColumnName = "UserID")
    private User ownerUser; // Assumes you have a User entity

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    // Constructors
    public Contact() {
    }

    // Getters and Setters
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
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
}