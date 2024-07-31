package com.likelion.runtale.domain.scenario.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.likelion.runtale.domain.running.entity.Running;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title; // 시나리오 제목

    @Column(length = 500)
    private String description; // 시나리오 설명

    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ScenarioStep> steps; // 시나리오 단계들
}
