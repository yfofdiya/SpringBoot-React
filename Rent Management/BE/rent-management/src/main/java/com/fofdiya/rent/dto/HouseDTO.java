package com.fofdiya.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
    private String houseId;
    private String address;
    private String createdDate;
    private String updatedDate;
}
