package org.example.bsaas.dto;

public class PipelineStageResponseDTO {

    private Integer id;
    private String name;
    private String description;

    // getters e setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}