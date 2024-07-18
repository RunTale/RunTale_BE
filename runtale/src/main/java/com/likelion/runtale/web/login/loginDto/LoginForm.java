package com.likelion.runtale.web.login.loginDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {
    private String loginId;
    private String password;

}
