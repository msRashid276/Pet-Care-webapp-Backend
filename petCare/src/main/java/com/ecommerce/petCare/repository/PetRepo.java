package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet,Long> {



}
