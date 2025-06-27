package org.example.bsaas.dto;

public class UserResponseDTO {

    private Integer userId;
    private String name;
    private String email;

    // getters e setters

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}