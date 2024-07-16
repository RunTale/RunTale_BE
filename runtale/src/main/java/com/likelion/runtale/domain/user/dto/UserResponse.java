package com.likelion.runtale.domain.user.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String loginId;
    private String nickname;
}
