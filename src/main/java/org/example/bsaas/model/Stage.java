package org.example.bsaas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StageID")
    private Integer stageId;

    // other fields, getters, setters
    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}