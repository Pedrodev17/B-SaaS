package org.example.bsaas.repository;

import org.example.bsaas.model.PipelineStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PipelineStageRepository extends JpaRepository<PipelineStage, Integer> {

    Optional<PipelineStage> findByStageName(String stageName);

    List<PipelineStage> findAllByOrderByStageOrderAsc();

    Optional<PipelineStage> findByIsDefaultTrue();

    // Opcional: busca paginada
    Page<PipelineStage> findAllByOrderByStageOrderAsc(Pageable pageable);

    // Opcional: busca combinada
    Optional<PipelineStage> findByStageNameAndIsDefaultTrue(String stageName);
}