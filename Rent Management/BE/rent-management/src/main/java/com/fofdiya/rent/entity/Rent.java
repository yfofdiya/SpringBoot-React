package com.fofdiya.rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    @Id
    @Column(name = "id")
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

    private String createdDate;

    private String updatedDate;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
