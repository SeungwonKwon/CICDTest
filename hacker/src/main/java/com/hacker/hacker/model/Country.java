package com.hacker.hacker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="country_id")
    private Long countryId;

    @Column(unique = true)
    private String countryCode;

    @Column
    private String countryName;
}
