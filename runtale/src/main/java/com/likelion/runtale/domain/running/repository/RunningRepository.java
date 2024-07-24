package com.likelion.runtale.domain.running.repository;

import com.likelion.runtale.domain.running.entity.Running;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunningRepository extends JpaRepository<Running, Long> {
    @Query("SELECT r FROM Running r JOIN FETCH r.user WHERE r.user.id = :userId")
    List<Running> findByUserIdWithUser(Long userId);

    @Query("SELECT r FROM Running r JOIN FETCH r.user")
    List<Running> findAllWithUser();
}
