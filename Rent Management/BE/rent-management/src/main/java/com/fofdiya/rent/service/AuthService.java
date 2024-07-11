package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.LoginDTO;
import com.fofdiya.rent.dto.RegisterDTO;
import com.fofdiya.rent.util.ApiResponse;
import com.fofdiya.rent.util.LoginResponse;

public interface AuthService {
    ApiResponse signUp(RegisterDTO registerDTO);
    LoginResponse login(LoginDTO loginDTO);
}
