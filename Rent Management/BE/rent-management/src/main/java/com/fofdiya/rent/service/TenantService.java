package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.TenantDTO;

import java.util.List;

public interface TenantService {
    TenantDTO createTenant(TenantDTO tenantDTO);

    List<TenantDTO> getAllTenant();

    TenantDTO getTenantById(String tenantId);

    TenantDTO updateTenant(String tenantId, TenantDTO tenantDTO);

    void deleteTenantById(String tenantId);

    List<TenantDTO> getAllTenantsByHouseId(String houseId);
}
