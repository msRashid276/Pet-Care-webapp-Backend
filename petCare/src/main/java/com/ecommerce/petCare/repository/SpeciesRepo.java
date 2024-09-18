package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepo extends JpaRepository<Species,Long> {
    Optional<Species> findByNameIgnoreCase(String name);
}
