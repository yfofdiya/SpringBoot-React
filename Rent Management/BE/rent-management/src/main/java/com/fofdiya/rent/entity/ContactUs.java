package com.fofdiya.rent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "all_queries")
public class ContactUs {
    @Id
    private String queryId;
    private String name;
    private String mobileNumber;
    private String message;
}
