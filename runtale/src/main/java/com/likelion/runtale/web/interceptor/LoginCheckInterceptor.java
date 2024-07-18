package com.likelion.runtale.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.runtale.web.SessionConst;
import com.likelion.runtale.web.login.loginDto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            // JSON 응답 설정
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // JSON 응답 객체 생성
            ApiResponse<Void> apiResponse = new ApiResponse<>(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.", null);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(apiResponse);

            // JSON 응답 전송
            response.getWriter().write(jsonResponse);
            return false;
        }

        return true;
    }
}
