package com.fofdiya.rent.controller;

import com.fofdiya.rent.dto.ContactUsDTO;
import com.fofdiya.rent.service.ContactUsService;
import com.fofdiya.rent.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact-us")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<ApiResponse> createQuery(@RequestBody ContactUsDTO contactUsDTO) {
        ContactUsDTO query = contactUsService.createQuery(contactUsDTO);
        return new ResponseEntity<>(ApiResponse
                .builder()
                .data(query)
                .message("Your inquiry sent")
                .build(), HttpStatus.CREATED);
    }
}
