package com.likelion.runtale.domain.scenario.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ScenarioStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scenario_id", nullable = false)
    @JsonBackReference
    private Scenario scenario;

    @Column(nullable = false)
    private int stepNumber; // 단계 번호

    @Column(nullable = false, length = 255)
    private String audioUrl; // 오디오 URL

    @Column(length = 255)
    private String videoUrl; // 비디오 URL (옵션)

    @Column(length = 1000)
    private String text; // 단계 설명
}
