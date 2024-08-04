package com.likelion.runtale.domain.running.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.runtale.domain.running.dto.RunningRequest;
import com.likelion.runtale.domain.scenario.entity.Scenario;
import com.likelion.runtale.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Running extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "running_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = true)
    @JsonIgnore
    private Scenario scenario;

    private LocalDateTime endTime;

    private Double distance; // km 단위

    private Double pace; // 분/km 단위

    private Double targetPace;

    private Double targetDistance;

    @Enumerated(EnumType.STRING)
    private RunningStatus status;

    @ElementCollection
    @CollectionTable(name = "running_locations", joinColumns = @JoinColumn(name = "running_id"))
    private List<Location> locations = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "running_milestones", joinColumns = @JoinColumn(name = "running_id"))
    private List<Integer> milestones = new ArrayList<>();
}
