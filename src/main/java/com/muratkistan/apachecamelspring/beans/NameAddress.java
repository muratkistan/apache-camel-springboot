package com.muratkistan.apachecamelspring.beans;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "NAME_ADDRESS")
public class NameAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "house_number")
    private String houseNumber;
    private String city;
    private String province;
    @Column(name = "postal_code")
    private String postalCode;
}
