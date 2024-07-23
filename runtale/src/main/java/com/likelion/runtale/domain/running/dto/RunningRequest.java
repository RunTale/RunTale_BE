package com.likelion.runtale.domain.running.dto;

import com.likelion.runtale.domain.running.entity.Running;
import com.likelion.runtale.domain.running.entity.RunningStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RunningRequest {
    private LocalDateTime endTime;
    private Double distance; // km 단위
    private Double pace; // 분/km 단위

    public Running toRunning() {
        Running running = new Running();
        running.setEndTime(this.endTime);
        running.setDistance(this.distance);
        running.setPace(this.pace);
        running.setStatus(this.endTime == null ? RunningStatus.IN_PROGRESS : RunningStatus.COMPLETED);
        return running;
    }
}