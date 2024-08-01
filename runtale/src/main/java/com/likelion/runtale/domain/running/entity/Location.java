package com.likelion.runtale.domain.running.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
public class Location {
    private Double latitude;
    private Double longitude;
}
