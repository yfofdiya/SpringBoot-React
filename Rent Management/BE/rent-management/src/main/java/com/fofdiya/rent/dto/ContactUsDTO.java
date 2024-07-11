package com.fofdiya.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsDTO {
    private String queryId;
    private String name;
    private String mobileNumber;
    private String message;
}
