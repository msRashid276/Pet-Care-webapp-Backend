package com.ecommerce.petCare.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "pet_shop_id"})
        }
)
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "species",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Pet> pets;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "pet_shop_id")
    private PetShop petShop;

}
