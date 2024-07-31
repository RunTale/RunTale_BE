package com.likelion.runtale.domain.running.dto;

import com.likelion.runtale.domain.running.entity.Running;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RunningStatsResponse {
    private List<Running> runningList;
    private int targetPaceAchievedCount;
    private int targetDistanceAchievedCount;
    private double totalDistance;
    private int totalRunningCount;
    private double averagePace;
}