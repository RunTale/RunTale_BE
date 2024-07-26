package com.likelion.runtale.domain.running.entity;

import com.likelion.runtale.domain.running.dto.RunningRequest;
import com.likelion.runtale.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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
    private User user;

    private LocalDateTime endTime;

    private Double distance; // km 단위

    private Double pace; // 분/km 단위

    @Enumerated(EnumType.STRING)
    private RunningStatus status;

    public void updateRunning(RunningRequest runningRequest) {
        this.endTime = runningRequest.getEndTime();
        this.distance = runningRequest.getDistance();
        this.pace = runningRequest.getPace();
        this.status = runningRequest.getEndTime() == null ? RunningStatus.IN_PROGRESS : RunningStatus.COMPLETED;
        this.setModifiedAt(LocalDateTime.now());
    }
}