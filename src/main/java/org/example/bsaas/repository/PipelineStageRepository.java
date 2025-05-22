package org.example.bsaas.repository;

import org.example.bsaas.model.PipelineStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PipelineStageRepository extends JpaRepository<PipelineStage, Integer> {

    /**
     * Finds a pipeline stage by its name.
     * @param stageName The name of the stage.
     * @return An Optional containing the PipelineStage if found.
     */
    Optional<PipelineStage> findByStageName(String stageName);

    /**
     * Finds all pipeline stages ordered by their stageOrder.
     * @return A list of PipelineStages sorted by stageOrder.
     */
    List<PipelineStage> findAllByOrderByStageOrderAsc();

    /**
     * Finds the default pipeline stage.
     * @return An Optional containing the default PipelineStage if one is set.
     */
    Optional<PipelineStage> findByIsDefaultTrue();
}