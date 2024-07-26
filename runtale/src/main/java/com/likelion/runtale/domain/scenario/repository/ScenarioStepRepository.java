package com.likelion.runtale.domain.scenario.repository;

import com.likelion.runtale.domain.scenario.entity.ScenarioStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScenarioStepRepository extends JpaRepository<ScenarioStep, Long> {
    Optional<ScenarioStep> findByScenarioIdAndStepNumber(Long scenarioId, int stepNumber);
}
