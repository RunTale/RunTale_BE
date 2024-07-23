package com.likelion.runtale.domain.scenario.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ScenarioStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    private int stepNumber;          // 단계 번호
    private String audioUrl;         // 오디오 URL
    private String videoUrl;         // 비디오 URL (옵션)
    private String text;             // 단계 설명

    @OneToMany(mappedBy = "scenarioStep", cascade = CascadeType.ALL)
    private List<ScenarioTrigger> triggers; // 트리거들
}
