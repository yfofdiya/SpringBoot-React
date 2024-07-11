package com.fofdiya.rent.service;

import com.fofdiya.rent.dto.ContactUsDTO;
import com.fofdiya.rent.entity.ContactUs;
import com.fofdiya.rent.repository.ContactUsRepository;
import com.fofdiya.rent.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private static final Converter<ContactUsDTO, ContactUs> contactUsConverter = new Converter<>(ContactUsDTO.class, ContactUs.class);

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public ContactUsDTO createQuery(ContactUsDTO contactUsDTO) {
        ContactUs contactUs = new ContactUs();
        contactUs.setQueryId(UUID.randomUUID().toString());
        contactUs.setName(contactUsDTO.getName());
        contactUs.setMobileNumber(contactUsDTO.getMobileNumber());
        contactUs.setMessage(contactUsDTO.getMessage());
        ContactUs createdQuery = contactUsRepository.save(contactUs);
        return contactUsConverter.convertToDto(createdQuery);
    }
}
