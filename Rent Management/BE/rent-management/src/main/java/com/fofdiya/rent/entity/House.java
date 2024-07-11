package com.fofdiya.rent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class House {

    @Id
    private String houseId;

    @Column(columnDefinition = "TEXT")
    private String houseAddress;

    private String createdDate;

    private String updatedDate;

    @OneToMany(mappedBy = "house")
    private List<Tenant> tenantList = new ArrayList<>();
}
