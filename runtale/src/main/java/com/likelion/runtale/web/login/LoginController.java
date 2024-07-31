package com.likelion.runtale.web.login;

import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.common.response.SuccessMessage;
import com.likelion.runtale.domain.login.LoginService;
import com.likelion.runtale.domain.user.entity.User;
import com.likelion.runtale.web.SessionConst;
import com.likelion.runtale.web.login.loginDto.LoginForm;
import com.likelion.runtale.web.login.loginDto.UserData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Login", description = "login 관련 API")
@Controller
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserData>> login(@RequestBody LoginForm form,
                                                       HttpServletRequest request) {

        User loginUser = loginService.login(form.getLoginId(), form.getPassword());

        if (loginUser == null) {
            // 로그인 실패인 경우
            return ResponseEntity.status(ErrorMessage.USER_LOGIN_FAILED.getHttpStatus())
                    .body(ApiResponse.error(ErrorMessage.USER_LOGIN_FAILED));
            // return이 안 돼있어서 항상 200 반환했었음.
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        UserData userData = null;
        if (loginUser != null) {
            userData = new UserData(loginUser.getId());
        }
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.USER_LOGIN_SUCCESS, userData));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<UserData>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(ApiResponse.success(SuccessMessage.USER_LOGOUT_SUCCESS));
    }
}
