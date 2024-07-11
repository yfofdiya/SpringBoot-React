package com.fofdiya.rent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

    @Id
    @Column(name = "id")
    private String tenantId;

    @Column(name = "name")
    private String tenantName;

    @Column(name = "email")
    private String tenantEmail;

    @Column(name = "number")
    private String mobileNumber;

    @Column(columnDefinition = "TEXT")
    private String permanentAddress;

    @Column(columnDefinition = "TEXT")
    private String presentAddress;

    @Column(name = "dob")
    private String dateOfBirth;

    private String idProofName;

    private String idProofNumber;

    // Date when tenant started staying
    private String tenantStartDate;

    // Is tenant still staying?
    private boolean tenantLive;

    // If isTenantLive = true then no need to add this date, else Date when tenant ended staying
    private String tenantEndDate;

    private String createdDate;

    private String updatedDate;

    @OneToMany(mappedBy = "tenant")
    private List<Rent> rentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
