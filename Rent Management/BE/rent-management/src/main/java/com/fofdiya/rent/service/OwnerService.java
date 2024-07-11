package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.OwnerDTO;
import com.fofdiya.rent.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    OwnerDTO createOwner(OwnerDTO ownerDTO);
    List<OwnerDTO> getAllOwner();
    OwnerDTO getOwnerByOwnerId(String ownerId);
    OwnerDTO updateOwner(String ownerId, OwnerDTO ownerDTO);
    void deleteOwner(String ownerId);
}
