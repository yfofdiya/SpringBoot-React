package com.fofdiya.rent.exception;

import com.fofdiya.rent.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleOwnerNotFoundException(OwnerNotFoundException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<ApiResponse> handleTenantNotFoundException(TenantNotFoundException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentNotFoundException.class)
    public ResponseEntity<ApiResponse> handleRentNotFoundException(RentNotFoundException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<ApiResponse> handleHouseNotFoundException(HouseNotFoundException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJWTTokenException.class)
    public ResponseEntity<ApiResponse> handleInvalidJWTTokenException(InvalidJWTTokenException e) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
