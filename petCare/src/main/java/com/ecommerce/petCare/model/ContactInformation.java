package com.ecommerce.petCare.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContactInformation {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobile;

    private String twitter;

    private String instagram;

}
