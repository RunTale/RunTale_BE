package com.likelion.runtale.domain.running.dto;

import com.likelion.runtale.domain.running.entity.Running;
import com.likelion.runtale.domain.running.entity.RunningStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class RunningResponse {
    private Long id;
    private LocalDateTime endTime;
    private Double distance; // km 단위
    private Double pace;
    private Double targetPace;
    private Double targetDistance;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long userId;
    private Long scenarioId;
    private RunningStatus status;
    private List<LocationResponse> locations;
    private String audioUrl;

    public RunningResponse(Running running) {
        this.id = running.getId();
        this.endTime = running.getEndTime();
        this.distance = running.getDistance();
        this.pace = running.getPace();
        this.createdDate = running.getCreatedDate();
        this.lastModifiedDate = running.getLastModifiedDate();
        this.userId = running.getUser().getId();
        this.status = running.getStatus();
        this.targetDistance = running.getTargetDistance();
        this.targetPace = running.getTargetPace();
        this.scenarioId = running.getScenario() != null ? running.getScenario().getId() : null; // null 검사
        this.locations = running.getLocations().stream()
                .map(LocationResponse::new)
                .collect(Collectors.toList());
    }
    public RunningResponse(Running running, String audioUrl) {
        this(running);
        this.audioUrl = audioUrl; // 오디오 URL 설정
    }
}
