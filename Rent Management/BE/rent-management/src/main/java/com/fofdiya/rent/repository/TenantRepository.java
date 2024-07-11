package com.fofdiya.rent.repository;

import com.fofdiya.rent.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, String> {

    @Query(value = "select * from tenant t where t.house_id =:houseId", nativeQuery = true)
    List<Tenant> findByHouseId(String houseId);
}
