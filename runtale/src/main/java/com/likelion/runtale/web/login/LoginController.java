package com.likelion.runtale.web.login;

import com.likelion.runtale.domain.login.LoginService;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.web.SessionConst;
import com.likelion.runtale.web.login.loginDto.ApiResponse;
import com.likelion.runtale.web.login.loginDto.LoginForm;
import com.likelion.runtale.web.login.loginDto.UserData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserData>> login(@RequestBody LoginForm form,
                                             HttpServletRequest request) {

        User loginUser = loginService.login(form.getLoginId(), form.getPassword());

        if (loginUser == null) {
            // 로그인 실패인 경우
            ApiResponse<UserData> response = new ApiResponse<>(401, "로그인 실패", null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        UserData userData = new UserData(loginUser.getId());
        ApiResponse<UserData> response = new ApiResponse<>(HttpStatus.OK.value(), "로그인 성공", userData);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<UserData>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "로그아웃 성공", null));
    }
}
