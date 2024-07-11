package com.fofdiya.rent.repository;

import com.fofdiya.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, String> {

    @Query(value = "select * from rent r where r.tenant_id =:tenantId", nativeQuery = true)
    List<Rent> findByTenantId(String tenantId);
}
