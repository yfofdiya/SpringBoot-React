package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.HouseDTO;
import com.fofdiya.rent.entity.House;
import com.fofdiya.rent.exception.HouseNotFoundException;
import com.fofdiya.rent.repository.HouseRepository;
import com.fofdiya.rent.util.Converter;
import com.fofdiya.rent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HouseServiceImpl implements HouseService {

    private static final Converter<HouseDTO, House> houseConverter = new Converter<>(HouseDTO.class, House.class);

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public HouseDTO createHouse(HouseDTO houseDTO) {
        House house = new House();
        house.setHouseId(UUID.randomUUID().toString());
        house.setHouseAddress(houseDTO.getAddress());
        house.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        house.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        House createdHouse = houseRepository.save(house);
        return houseConverter.convertToDto(createdHouse);
    }

    @Override
    public List<HouseDTO> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream().map(houseConverter::convertToDto).toList();
    }

    @Override
    public HouseDTO getHouseByHouseId(String houseId) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House", "House Id", houseId));
        return houseConverter.convertToDto(house);
    }

    @Override
    public HouseDTO updateHouse(String houseId, HouseDTO houseDTO) {
        HouseDTO dto = getHouseByHouseId(houseId);
        House house = houseConverter.convertToEntity(dto);
        house.setHouseAddress(houseDTO.getAddress());
        house.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        House updatedHouse = houseRepository.save(house);
        return houseConverter.convertToDto(updatedHouse);
    }

    @Override
    public void deleteHouseByHouseId(String houseId) {
        HouseDTO dto = getHouseByHouseId(houseId);
        House house = houseConverter.convertToEntity(dto);
        houseRepository.delete(house);
    }
}
