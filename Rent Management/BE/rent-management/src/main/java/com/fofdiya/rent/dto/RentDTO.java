package com.fofdiya.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO {
    private String rentId;
    private long totalRent;
    private long totalPaid;
    private long remainingRentAmount;
    // Date when first rent started
    private String rentStartDate;
    // Date when next rent need to pay
    private String nextRentPayDate;
    // Date when last rent paid
    private String lastRentPayDate;
    private String tenantId;
    private String createdDate;
    private String updatedDate;
}
