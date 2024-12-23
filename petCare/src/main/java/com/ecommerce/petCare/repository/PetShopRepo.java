package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.PetShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetShopRepo extends JpaRepository<PetShop,Long> {

        @Query("SELECT p FROM PetShop p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<PetShop> findPetShopBySearchQuery(String keyword);

        Optional<PetShop> findByOwnerId(Long userId);
}
