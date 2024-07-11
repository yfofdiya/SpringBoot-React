package com.practice.blog.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.blog.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", "Something went wrong with JWT token, It might get expired or invalid"))
                .success(false)
                .build();
        String jsonString = objectMapper.writeValueAsString(apiResponse);
        PrintWriter writer = response.getWriter();
        writer.println(jsonString);
    }
}
