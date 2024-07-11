package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.RentDTO;
import com.fofdiya.rent.service.RentService;
import com.fofdiya.rent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody RentDTO rentDTO) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(rentService.createRent(rentDTO))
                .message("Rent created!").build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(rentService.getAllRents())
                .message("Fetched all rents!").build(), HttpStatus.OK);
    }

    @GetMapping("/{rentId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable String rentId) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(rentService.getRentById(rentId))
                .message("Fetched rent by id!").build(), HttpStatus.OK);
    }

    @PutMapping("/{rentId}")
    public ResponseEntity<ApiResponse> update(@PathVariable String rentId, @RequestBody RentDTO rentDTO) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(rentService.updateRent(rentId, rentDTO))
                .message("Rent updated!").build(), HttpStatus.OK);
    }

    @DeleteMapping("/{rentId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable String rentId) {
        rentService.deleteRentById(rentId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message("Rent deleted!").build(), HttpStatus.OK);
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<ApiResponse> getAllRentsByTenantId(@PathVariable String tenantId) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(rentService.getAllRentsByTenantId(tenantId))
                .message("Fetched all rents by tenant id!").build(), HttpStatus.OK);
    }
}
