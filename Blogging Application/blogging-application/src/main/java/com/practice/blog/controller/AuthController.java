package com.practice.blog.controller;

import com.practice.blog.dto.JWTRequestDto;
import com.practice.blog.dto.UserDto;
import com.practice.blog.jwt.JWTService;
import com.practice.blog.service.CustomUserDetailsService;
import com.practice.blog.service.UserService;
import com.practice.blog.util.ApiResponse;
import com.practice.blog.util.JWTApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Authentication API for Login and Register")
@Slf4j
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JWTApiResponse> createToken(@RequestBody JWTRequestDto jwtRequestDto) {
        log.info("Checking Username and Password");
        authenticate(jwtRequestDto.getUsername(), jwtRequestDto.getPassword());
        log.info("Authentication successful");

        log.info("Fetching user details for the {}", jwtRequestDto.getUsername());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequestDto.getUsername());
        UserDto userDto = userService.getUserByUsername(jwtRequestDto.getUsername());
        log.info("User details fetched for the {}", jwtRequestDto.getUsername());

        log.info("Token generation started...");
        String jwtToken = jwtService.generateToken(userDetails);
        log.info("Token generation completed...");

        JWTApiResponse apiResponse = JWTApiResponse
                .builder()
                .token(jwtToken)
                .user(userDto)
                .build();

        log.info("Login successful");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(token);

    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserDto userDto) {
        log.info("User registration started...");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(userService.createUser(userDto))
                .message(Map.of("success", "User is registered"))
                .success(true)
                .build();
        log.info("User registration completed...");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
