package com.likelion.runtale.domain.user.dto;

import com.likelion.runtale.domain.user.entity.User;
import lombok.Data;


@Data
public class UserRequest {
    private String loginId;
    private String password;
    private String nickname;


    public User toUser() {
        return new User(loginId,password,nickname);
    }
}
