package com.ecommerce.petCare.model;


import com.ecommerce.petCare.dto.PetShopFavouritesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<PetShopFavouritesDto> favourites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

}
