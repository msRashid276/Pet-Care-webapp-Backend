package com.ecommerce.petCare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private String breed;

    private int age;

    private String gender;

    private BigDecimal price;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    @Column(length = 1000)
    private String description;

    private boolean available;

    @ManyToOne
    private PetShop petShop;

    private LocalDateTime registrationDate;

}
