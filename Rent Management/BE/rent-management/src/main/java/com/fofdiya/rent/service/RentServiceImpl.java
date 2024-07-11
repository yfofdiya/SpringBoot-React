package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.RentDTO;
import com.fofdiya.rent.entity.Rent;
import com.fofdiya.rent.entity.Tenant;
import com.fofdiya.rent.exception.RentNotFoundException;
import com.fofdiya.rent.exception.TenantNotFoundException;
import com.fofdiya.rent.repository.RentRepository;
import com.fofdiya.rent.repository.TenantRepository;
import com.fofdiya.rent.util.Converter;
import com.fofdiya.rent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private TenantRepository tenantRepository;

    private static final Converter<RentDTO, Rent> rentConverter = new Converter<>(RentDTO.class, Rent.class);

    @Override
    public RentDTO createRent(RentDTO rentDTO) {
        Rent rent = new Rent();
        rent.setRentId(UUID.randomUUID().toString());
        rent.setTotalRent(rentDTO.getTotalRent());
        rent.setTotalPaid(rentDTO.getTotalPaid());
        rent.setRemainingRentAmount(rentDTO.getRemainingRentAmount());
        rent.setRentStartDate(rentDTO.getRentStartDate());
        rent.setNextRentPayDate(DateUtils.addOneMonth(rentDTO.getRentStartDate()));
        addLastRentPayDate(rentDTO, rent);
        rent.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        rent.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Tenant tenant = tenantRepository.findById(rentDTO.getTenantId()).orElseThrow(() -> new TenantNotFoundException("Tenant", "Tenant Id", rentDTO.getTenantId()));
        rent.setTenant(tenant);
        Rent createdRent = rentRepository.save(rent);
        return rentConverter.convertToDto(createdRent);
    }

    private void addLastRentPayDate(RentDTO rentDTO, Rent rent) {
        List<Rent> rents = rentRepository.findByTenantId(rentDTO.getTenantId());
        if (!rents.isEmpty()) {
            rent.setLastRentPayDate(DateUtils.minusOneMonth(rentDTO.getRentStartDate()));
        } else {
            rent.setLastRentPayDate(null);
        }
    }


    @Override
    public List<RentDTO> getAllRents() {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream().map(rentConverter::convertToDto).toList();
    }

    @Override
    public RentDTO getRentById(String rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new RentNotFoundException("Rent", "Rent Id", rentId));
        return rentConverter.convertToDto(rent);
    }

    @Override
    public RentDTO updateRent(String rentId, RentDTO rentDTO) {
        RentDTO dto = getRentById(rentId);
        Rent rent = rentConverter.convertToEntity(dto);
        rent.setTotalRent(rentDTO.getTotalRent());
        rent.setTotalPaid(rentDTO.getTotalPaid());
        rent.setRemainingRentAmount(rentDTO.getRemainingRentAmount());
        rent.setRentStartDate(rentDTO.getRentStartDate());
        rent.setNextRentPayDate(DateUtils.addOneMonth(rentDTO.getRentStartDate()));
        rent.setLastRentPayDate(rent.getLastRentPayDate());
        rent.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Tenant tenant = tenantRepository.findById(rentDTO.getTenantId()).orElseThrow(() -> new TenantNotFoundException("Tenant", "Tenant Id", rentDTO.getTenantId()));
        rent.setTenant(tenant);
        Rent updatedRent = rentRepository.save(rent);
        return rentConverter.convertToDto(updatedRent);
    }

    @Override
    public void deleteRentById(String rentId) {
        RentDTO rentDTO = getRentById(rentId);
        Rent rent = rentConverter.convertToEntity(rentDTO);
        rentRepository.delete(rent);
    }

    @Override
    public List<RentDTO> getAllRentsByTenantId(String tenantId) {
        List<Rent> rents = rentRepository.findByTenantId(tenantId);
        return rents.stream().map(rentConverter::convertToDto).toList();
    }
}
