package org.example.bsaas.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PipelineStages")
public class PipelineStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StageID")
    private Integer stageId;

    @Column(name = "StageName", nullable = false, unique = true, length = 50)
    private String stageName;

    @Column(name = "StageOrder", nullable = false, unique = true)
    private Integer stageOrder;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "IsDefault")
    private Boolean isDefault; // In SQL, this was BOOLEAN DEFAULT FALSE

    @Column(name = "CreatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    // Constructors
    public PipelineStage() {
    }

    public PipelineStage(String stageName, Integer stageOrder, String description, Boolean isDefault) {
        this.stageName = stageName;
        this.stageOrder = stageOrder;
        this.description = description;
        this.isDefault = isDefault;
    }

    // Getters and Setters
    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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

    // It's good practice to also override equals(), hashCode(), and toString()
    // For brevity, they are omitted here but should be considered for a full implementation.

    @Override
    public String toString() {
        return "PipelineStage{" +
                "stageId=" + stageId +
                ", stageName='" + stageName + '\'' +
                ", stageOrder=" + stageOrder +
                ", description='" + description + '\'' +
                ", isDefault=" + isDefault +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}