package com.likelion.runtale.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.web.SessionConst;
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
            ApiResponse<Void> apiResponse = ApiResponse.error(ErrorMessage.USER_NOT_LOGGED_IN);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(apiResponse);

            // JSON 응답 전송
            response.getWriter().write(jsonResponse);
            return false;
        }

        return true;
    }
}
