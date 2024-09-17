package com.ecommerce.petCare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PetShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    private Users owner;

    private String name;

    private String description;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;



}
