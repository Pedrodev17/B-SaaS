package org.example.bsaas.dto;

import jakarta.validation.constraints.NotBlank;

public class PipelineStageRequestDTO {

    @NotBlank
    private String name;

    private String description;

    // getters e setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}