package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet,Long> {

        List<Pet> findByPetShopId(Long petShopId);

        @Query("SELECT p FROM Pet p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "OR LOWER(p.breed) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<Pet> findPetBySearchQuery(String keyword);

}
