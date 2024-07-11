package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.TenantDTO;
import com.fofdiya.rent.entity.House;
import com.fofdiya.rent.entity.Tenant;
import com.fofdiya.rent.exception.HouseNotFoundException;
import com.fofdiya.rent.exception.TenantNotFoundException;
import com.fofdiya.rent.repository.HouseRepository;
import com.fofdiya.rent.repository.TenantRepository;
import com.fofdiya.rent.util.Converter;
import com.fofdiya.rent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private HouseRepository houseRepository;

    private static final Converter<TenantDTO, Tenant> tenantConverter = new Converter<>(TenantDTO.class, Tenant.class);

    @Override
    public TenantDTO createTenant(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(UUID.randomUUID().toString());
        tenant.setTenantName(tenantDTO.getTenantName());
        tenant.setTenantEmail(tenantDTO.getTenantEmail());
        tenant.setMobileNumber(tenantDTO.getMobileNumber());
        tenant.setPermanentAddress(tenantDTO.getPermanentAddress());
        tenant.setPresentAddress(tenantDTO.getPresentAddress());
        tenant.setDateOfBirth(tenantDTO.getDateOfBirth());
        tenant.setIdProofName(tenantDTO.getIdProofName());
        tenant.setIdProofNumber(tenantDTO.getIdProofNumber());
        tenant.setTenantStartDate(tenantDTO.getTenantStartDate());
        tenant.setTenantLive(tenantDTO.isTenantLive());
        if (tenantDTO.isTenantLive()) {
            tenant.setTenantEndDate(null);
        }
        House house = houseRepository.findById(tenantDTO.getHouseId()).orElseThrow(() -> new HouseNotFoundException("House", "House Id", tenantDTO.getHouseId()));
        tenant.setHouse(house);
        tenant.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        tenant.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Tenant createdTenant = tenantRepository.save(tenant);
        return tenantConverter.convertToDto(createdTenant);
    }

    @Override
    public List<TenantDTO> getAllTenant() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream().map(tenantConverter::convertToDto).toList();
    }

    @Override
    public TenantDTO getTenantById(String tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(() -> new TenantNotFoundException("Tenant", "Tenant Id", tenantId));
        return tenantConverter.convertToDto(tenant);
    }

    @Override
    public TenantDTO updateTenant(String tenantId, TenantDTO tenantDTO) {
        TenantDTO dto = getTenantById(tenantId);
        Tenant tenant = tenantConverter.convertToEntity(dto);
        tenant.setTenantName(tenantDTO.getTenantName());
        tenant.setTenantEmail(tenantDTO.getTenantEmail());
        tenant.setMobileNumber(tenantDTO.getMobileNumber());
        tenant.setPermanentAddress(tenantDTO.getPermanentAddress());
        tenant.setPresentAddress(tenantDTO.getPresentAddress());
        tenant.setDateOfBirth(tenantDTO.getDateOfBirth());
        tenant.setIdProofName(tenantDTO.getIdProofName());
        tenant.setIdProofNumber(tenantDTO.getIdProofNumber());
        tenant.setTenantStartDate(tenantDTO.getTenantStartDate());
        tenant.setTenantLive(tenantDTO.isTenantLive());
        if (!tenantDTO.isTenantLive()) {
            tenant.setTenantEndDate(tenantDTO.getTenantEndDate());
        } else {
            tenant.setTenantEndDate(null);
        }
        House house = houseRepository.findById(tenantDTO.getHouseId()).orElseThrow(() -> new HouseNotFoundException("House", "House Id", tenantDTO.getHouseId()));
        tenant.setHouse(house);
        tenant.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Tenant updatedTenant = tenantRepository.save(tenant);
        return tenantConverter.convertToDto(updatedTenant);
    }

    @Override
    public void deleteTenantById(String tenantId) {
        TenantDTO dto = getTenantById(tenantId);
        Tenant tenant = tenantConverter.convertToEntity(dto);
        tenantRepository.delete(tenant);
    }

    @Override
    public List<TenantDTO> getAllTenantsByHouseId(String houseId) {
        List<Tenant> tenants = tenantRepository.findByHouseId(houseId);
        return tenants.stream().map(tenantConverter::convertToDto).toList();
    }
}