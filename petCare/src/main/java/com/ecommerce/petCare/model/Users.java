package com.ecommerce.petCare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false,unique = true)
    private String lastName;

    @Column(nullable = false)
    private String email;

//    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role;

}
