package com.fofdiya.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO {
    private String tenantId;
    private String tenantName;
    private String tenantEmail;
    private String mobileNumber;
    private String permanentAddress;
    private String presentAddress;
    private String dateOfBirth;
    private String idProofName;
    private String idProofNumber;
    // Date when tenant started staying
    private String tenantStartDate;
    // Is tenant still staying?
    private boolean tenantLive;
    // If isTenantLive = true then no need to add this date, else Date when tenant ended staying
    private String tenantEndDate;
    private String houseId;
    private String createdDate;
    private String updatedDate;
}
