package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.RentDTO;

import java.util.List;

public interface RentService {

    RentDTO createRent(RentDTO rentDTO);

    List<RentDTO> getAllRents();

    RentDTO getRentById(String rentId);

    RentDTO updateRent(String rentId, RentDTO rentDTO);

    void deleteRentById(String rentId);

    List<RentDTO> getAllRentsByTenantId(String tenantId);
}

