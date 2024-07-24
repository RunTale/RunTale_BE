package com.likelion.runtale.domain.tier.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;    // 티어 이름 (예: 달팽이, 거북이, 토끼, 말, 치타)

    @Column
    private String description;     // 티어 설명

    @Column
    private String imageUrl;
}
