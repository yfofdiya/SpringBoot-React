package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.OwnerDTO;
import com.fofdiya.rent.service.OwnerService;
import com.fofdiya.rent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOwner(@RequestBody OwnerDTO ownerDTO) {
        OwnerDTO owner = ownerService.createOwner(ownerDTO);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(owner)
                .message("Owner created!")
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<OwnerDTO> owners = ownerService.getAllOwner();
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(owners)
                .message("Fetched all owners!")
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable String ownerId) {
        OwnerDTO owner = ownerService.getOwnerByOwnerId(ownerId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(owner)
                .message("Fetched owner by id!")
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<ApiResponse> updateOwner(@PathVariable String ownerId, @RequestBody OwnerDTO ownerDTO) {
        OwnerDTO owner = ownerService.updateOwner(ownerId, ownerDTO);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(owner)
                .message("Owner updated!")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable String ownerId) {
        ownerService.deleteOwner(ownerId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message("Owner deleted!")
                .build(), HttpStatus.OK);
    }
}
