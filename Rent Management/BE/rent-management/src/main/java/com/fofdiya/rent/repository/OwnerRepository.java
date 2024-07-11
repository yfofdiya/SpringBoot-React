package com.fofdiya.rent.repository;

import com.fofdiya.rent.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String> {
    Optional<Owner> findByUsername(String username);
}
