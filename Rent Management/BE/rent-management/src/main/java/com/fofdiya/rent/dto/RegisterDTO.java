package com.fofdiya.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String ownerId;
    private String ownerName;
    private String ownerEmail;
    private String username;
    private String password;
    private String mobileNumber;
    private String address;
    private String dateOfBirth;
}
