package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.HouseDTO;

import java.util.List;

public interface HouseService {
    HouseDTO createHouse(HouseDTO houseDTO);

    List<HouseDTO> getAllHouses();

    HouseDTO getHouseByHouseId(String houseId);

    HouseDTO updateHouse(String houseId, HouseDTO houseDTO);

    void deleteHouseByHouseId(String houseId);
}
