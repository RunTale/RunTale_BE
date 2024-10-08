package com.likelion.runtale.domain.running.repository;

import com.likelion.runtale.domain.running.entity.Running;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RunningRepository extends JpaRepository<Running, Long> {
    @EntityGraph(attributePaths = "user")
    List<Running> findByUserId(Long userId);

    @EntityGraph(attributePaths = "user")
    List<Running> findAll();

    @EntityGraph(attributePaths = "user")
    @Query("SELECT r FROM Running r WHERE r.user.id = :userId AND r.createdDate BETWEEN :startDate AND :endDate")
    List<Running> findByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
