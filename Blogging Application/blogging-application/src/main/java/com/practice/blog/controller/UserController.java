package com.practice.blog.controller;

import com.practice.blog.dto.UserDto;
import com.practice.blog.service.UserService;
import com.practice.blog.util.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "CRUD operation for user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("User creation started");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(userService.createUser(userDto))
                .message(Map.of("success", "User created successfully"))
                .success(true)
                .build();
        log.info("User creation completed");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        log.info("Fetching all users");
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(userService.getAllUsers())
                .message(Map.of("success", "All users fetched"))
                .success(true)
                .build();
        log.info("Fetched all users");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") String id) {
        log.info("Fetching user with user id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(userService.getUserById(id))
                .message(Map.of("success", "User fetched by id " + id))
                .success(true)
                .build();
        log.info("Fetched user with user id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserDto userDto) {
        log.info("Updating user with user id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(userService.updateUser(id, userDto))
                .message(Map.of("success", "User updated successfully for id " + id))
                .success(true)
                .build();
        log.info("Updated user with user id {}", id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id) {
        log.info("Deleting user with user id {}", id);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .data(null)
                .message(Map.of("success", "User deleted successfully"))
                .success(true)
                .build();
        userService.deleteUserById(id);
        log.info("Deleted user with user id {}", id);
        return ResponseEntity.ok().body(apiResponse);
    }
}
