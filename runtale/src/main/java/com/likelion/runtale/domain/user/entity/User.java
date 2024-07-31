package com.likelion.runtale.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.likelion.runtale.domain.running.entity.Running;
import com.likelion.runtale.domain.tier.entity.Tier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private double score;

    private double percentile;

    private double progress;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Running> runnings;

    public User() {
    }

    public User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Long id, String loginId, String password, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public void addOrUpdateRunning(Running running) {
        for (int i = 0; i < runnings.size(); i++) {
            if (runnings.get(i).getId().equals(running.getId())) {
                runnings.set(i, running);
                return;
            }
        }
        this.runnings.add(running);
        running.setUser(this);
    }

public int getRunningDays(int year, int month) {
    YearMonth yearMonth = YearMonth.of(year, month);
    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();
    return (int) getRunnings().stream()
            .filter(running -> !running.getCreatedDate().toLocalDate().isBefore(startDate) &&
                    !running.getCreatedDate().toLocalDate().isAfter(endDate))
            .map(running -> running.getCreatedDate().toLocalDate())
            .distinct()
            .count();
}

    public double getTotalDistance(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return getRunnings().stream()
                .filter(running -> !running.getCreatedDate().toLocalDate().isBefore(startDate) &&
                        !running.getCreatedDate().toLocalDate().isAfter(endDate))
                .mapToDouble(Running::getDistance)
                .sum();
    }

    public double getAveragePace(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return getRunnings().stream()
                .filter(running -> !running.getCreatedDate().toLocalDate().isBefore(startDate) &&
                        !running.getCreatedDate().toLocalDate().isAfter(endDate))
                .mapToDouble(running -> running.getPace() != null ? running.getPace() : 0.0)
                .average()
                .orElse(0.0);
    }

    public List<Running> getRunnings() {
        return runnings != null ? runnings : Collections.emptyList();
    }
}
