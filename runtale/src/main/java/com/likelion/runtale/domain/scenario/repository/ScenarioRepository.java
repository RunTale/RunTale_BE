package com.likelion.runtale.domain.scenario.repository;

import com.likelion.runtale.domain.scenario.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}
