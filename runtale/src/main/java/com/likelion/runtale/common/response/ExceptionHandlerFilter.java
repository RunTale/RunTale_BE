package com.likelion.runtale.common.response;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.runtale.common.ApiResponse;
import com.likelion.runtale.common.exception.BaseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BaseException exception) {
            setErrorResponse(response, exception.getError());
        }
    }

    private void setErrorResponse(
            HttpServletResponse response,
            ErrorMessage errorMessage
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorMessage.getHttpStatusValue());
        response.setContentType(APPLICATION_JSON_VALUE);
        ApiResponse baseResponse = ApiResponse.error(errorMessage);

        try {
            response.getWriter()
                    .write(objectMapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true)
                            .writeValueAsString(baseResponse));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
