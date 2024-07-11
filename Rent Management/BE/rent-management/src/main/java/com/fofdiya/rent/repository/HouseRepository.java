package com.fofdiya.rent.repository;

import com.fofdiya.rent.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, String> {
}
