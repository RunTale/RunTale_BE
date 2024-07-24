package com.likelion.runtale.domain.running.repository;

import com.likelion.runtale.domain.running.entity.Running;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunningRepository extends JpaRepository<Running, Long> {
    @EntityGraph(attributePaths = "user")
    List<Running> findByUserId(Long userId);

    @EntityGraph(attributePaths = "user")
    List<Running> findAll();
}