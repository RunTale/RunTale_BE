package com.likelion.runtale.web;

import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.web.argumentresolver.Login;
import com.likelion.runtale.web.login.loginDto.ApiResponse;
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
            ApiResponse<UserData> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.",null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        UserData userData = new UserData(loginUser.getId());
        ApiResponse<UserData> response = new ApiResponse<>(HttpStatus.OK.value(), "로그인 상태입니다.",userData);
        return ResponseEntity.ok(response);
    }
}
