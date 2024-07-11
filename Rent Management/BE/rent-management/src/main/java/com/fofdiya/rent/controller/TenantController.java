package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.TenantDTO;
import com.fofdiya.rent.service.TenantService;
import com.fofdiya.rent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody TenantDTO tenantDTO) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(tenantService.createTenant(tenantDTO))
                .message("Tenant created!").build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(tenantService.getAllTenant())
                .message("Fetched all tenants!").build(), HttpStatus.OK);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable String tenantId) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(tenantService.getTenantById(tenantId))
                .message("Fetched tenant by id!").build(), HttpStatus.OK);
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<ApiResponse> update(@PathVariable String tenantId, @RequestBody TenantDTO tenantDTO) {
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(tenantService.updateTenant(tenantId, tenantDTO))
                .message("Tenant updated!").build(), HttpStatus.OK);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable String tenantId) {
        tenantService.deleteTenantById(tenantId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message("Tenant deleted!").build(), HttpStatus.OK);
    }

    @GetMapping("/house/{houseId}")
    public ResponseEntity<ApiResponse> getAllTenantsByHouseId(@PathVariable String houseId) {
        List<TenantDTO> tenants = tenantService.getAllTenantsByHouseId(houseId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(tenants)
                .message("Fetched tenants by house id!").build(), HttpStatus.OK);
    }
}
