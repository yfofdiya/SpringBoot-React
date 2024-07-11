package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.OwnerDTO;
import com.fofdiya.rent.entity.Owner;
import com.fofdiya.rent.exception.OwnerNotFoundException;
import com.fofdiya.rent.repository.OwnerRepository;
import com.fofdiya.rent.util.Converter;
import com.fofdiya.rent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Converter<OwnerDTO, Owner> ownerConverter = new Converter<>(OwnerDTO.class, Owner.class);

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setOwnerId(UUID.randomUUID().toString());
        owner.setOwnerName(ownerDTO.getOwnerName());
        owner.setOwnerEmail(ownerDTO.getOwnerEmail());
        owner.setUsername(ownerDTO.getUsername());
        owner.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));
        owner.setMobileNumber(ownerDTO.getMobileNumber());
        owner.setAddress(ownerDTO.getAddress());
        owner.setDateOfBirth(ownerDTO.getDateOfBirth());
        owner.setCreatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        owner.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Owner createdOwner = ownerRepository.save(owner);
        return ownerConverter.convertToDto(createdOwner);
    }

    @Override
    public List<OwnerDTO> getAllOwner() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream().map(ownerConverter::convertToDto).toList();
    }

    @Override
    public OwnerDTO getOwnerByOwnerId(String ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner", "Owner Id", ownerId));
        return ownerConverter.convertToDto(owner);
    }

    @Override
    public OwnerDTO updateOwner(String ownerId, OwnerDTO ownerDTO) {
        OwnerDTO dto = getOwnerByOwnerId(ownerId);
        Owner owner = ownerConverter.convertToEntity(dto);
        owner.setOwnerName(ownerDTO.getOwnerName());
        owner.setOwnerEmail(ownerDTO.getOwnerEmail());
        owner.setUsername(ownerDTO.getUsername());
        owner.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));
        owner.setMobileNumber(ownerDTO.getMobileNumber());
        owner.setAddress(ownerDTO.getAddress());
        owner.setDateOfBirth(ownerDTO.getDateOfBirth());
        owner.setUpdatedDate(DateUtils.convertDateToYYYYMMDDHHMMSSFormat(new Date()));
        Owner updatedOwner = ownerRepository.save(owner);
        return ownerConverter.convertToDto(updatedOwner);
    }

    @Override
    public void deleteOwner(String ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner", "Owner Id", ownerId));
        ownerRepository.delete(owner);
    }
}
