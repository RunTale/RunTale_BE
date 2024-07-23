package com.likelion.runtale.domain.scenario.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;            // 시나리오 제목
    private String description;      // 시나리오 설명

    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL)
    private List<ScenarioStep> steps; // 시나리오 단계들

}
