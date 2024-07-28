package com.likelion.runtale.web;

import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.common.response.SuccessMessage;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.web.argumentresolver.Login;
import com.likelion.runtale.web.login.loginDto.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

@GetMapping("/")
public ResponseEntity<ApiResponse<UserData>> homeLogin(@Login User loginUser) {
    if (loginUser == null) {
        return ResponseEntity.status(ErrorMessage.USER_NOT_LOGGED_IN.getHttpStatus())
                .body(ApiResponse.error(ErrorMessage.USER_NOT_LOGGED_IN));
    }

    UserData userData = new UserData(loginUser.getId());
    return ResponseEntity.ok(ApiResponse.success(SuccessMessage.USER_LOGIN_SUCCESS, userData));
    }
}
