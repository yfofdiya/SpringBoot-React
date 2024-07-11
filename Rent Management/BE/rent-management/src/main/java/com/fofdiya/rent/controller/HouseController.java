package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.HouseDTO;
import com.fofdiya.rent.service.HouseService;
import com.fofdiya.rent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody HouseDTO houseDTO) {
        HouseDTO house = houseService.createHouse(houseDTO);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(house)
                .message("House created!")
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<HouseDTO> houses = houseService.getAllHouses();
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(houses)
                .message("Fetched all houses!")
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable String houseId) {
        HouseDTO house = houseService.getHouseByHouseId(houseId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(house)
                .message("Fetched house by id!")
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<ApiResponse> updateHouse(@PathVariable String houseId, @RequestBody HouseDTO houseDTO) {
        HouseDTO house = houseService.updateHouse(houseId, houseDTO);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(house)
                .message("House updated!")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{houseId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable String houseId) {
        houseService.deleteHouseByHouseId(houseId);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(null)
                .message("House deleted!")
                .build(), HttpStatus.OK);
    }
}
