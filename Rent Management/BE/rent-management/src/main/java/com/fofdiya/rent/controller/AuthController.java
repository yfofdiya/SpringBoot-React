package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.LoginDTO;
import com.fofdiya.rent.dto.RegisterDTO;
import com.fofdiya.rent.service.AuthService;
import com.fofdiya.rent.util.ApiResponse;
import com.fofdiya.rent.util.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp(@RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.signUp(registerDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = authService.login(loginDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
