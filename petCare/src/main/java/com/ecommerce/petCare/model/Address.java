package com.ecommerce.petCare.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users user;

    private String street;

    private String city;

    private String state;

    private String zipCode;

}
