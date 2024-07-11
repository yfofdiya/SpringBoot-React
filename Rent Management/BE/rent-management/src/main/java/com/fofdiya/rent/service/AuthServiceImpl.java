package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.LoginDTO;
import com.fofdiya.rent.dto.RegisterDTO;
import com.fofdiya.rent.entity.Owner;
import com.fofdiya.rent.jwt.JWTService;
import com.fofdiya.rent.repository.OwnerRepository;
import com.fofdiya.rent.util.ApiResponse;
import com.fofdiya.rent.util.DateUtils;
import com.fofdiya.rent.util.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final OwnerRepository ownerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public AuthServiceImpl(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ApiResponse signUp(RegisterDTO registerDTO) {
        Owner owner = new Owner();
        owner.setOwnerId(UUID.randomUUID().toString());
        owner.setOwnerName(registerDTO.getOwnerName());
        owner.setOwnerEmail(registerDTO.getOwnerEmail());
        owner.setUsername(registerDTO.getUsername());
        owner.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        owner.setMobileNumber(registerDTO.getMobileNumber());
        owner.setAddress(registerDTO.getAddress());
        owner.setDateOfBirth(registerDTO.getDateOfBirth());
        owner.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        owner.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));

        Owner createdOwner = ownerRepository.save(owner);

        return ApiResponse
                .builder()
                .data(createdOwner)
                .message("Owner created!")
                .build();
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        Owner owner = ownerRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Owner not found"));

        String jwtToken = jwtService.generateToken(owner);

        return LoginResponse
                .builder()
                .token(jwtToken)
                .data(owner)
                .message("Successfully logged in")
                .build();
    }
}
