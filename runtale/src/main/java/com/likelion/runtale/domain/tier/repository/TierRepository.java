package com.likelion.runtale.domain.tier.repository;

import com.likelion.runtale.domain.tier.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TierRepository extends JpaRepository<Tier, Long> {
    Optional<Tier> findByName(String name);
}
