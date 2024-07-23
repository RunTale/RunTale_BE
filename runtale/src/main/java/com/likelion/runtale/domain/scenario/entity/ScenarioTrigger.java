package com.likelion.runtale.domain.scenario.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ScenarioTrigger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scenario_step_id")
    private ScenarioStep scenarioStep;

    private String type;             // 트리거 타입 (예: 시간, 거리, 이벤트)
    private double value;            // 트리거 값 (예: 5분, 1km)
}
