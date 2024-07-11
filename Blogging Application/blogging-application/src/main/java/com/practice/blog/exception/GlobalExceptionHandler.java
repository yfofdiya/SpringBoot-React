package com.practice.blog.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.blog.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", e.getMessage()))
                .success(false)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMessage = getAllErrorMessages(e);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(errorMessage)
                .success(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getAllErrorMessages(MethodArgumentNotValidException e) {
        Map<String, String> errorMessage = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String fieldMessage = error.getDefaultMessage();
            errorMessage.put(fieldName, fieldMessage);
        });
        return errorMessage;
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse> handleFileNotFoundException() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", "File is not available for download"))
                .success(false)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse> handleIOException() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", "Something went wrong while uploading or downloading the file"))
                .success(false)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentialsException() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", "Username or password is not correct"))
                .success(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiResponse> handleJsonProcessingException() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(Map.of("error", "Error occurred while converting string to JSON"))
                .success(false)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
