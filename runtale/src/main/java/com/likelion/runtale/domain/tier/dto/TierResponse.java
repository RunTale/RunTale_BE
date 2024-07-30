package com.likelion.runtale.domain.tier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TierResponse {
    private String nickname;
    private String tierName;
    private String description;
    private String imageUrl;
    private double percentile;
    private double progress;
}
