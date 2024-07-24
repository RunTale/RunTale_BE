package com.likelion.runtale.domain.tier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTierInfo {
    private String nickname;
    private String tier;
    private double percentile;
    private String imageUrl;
}
