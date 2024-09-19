package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepo extends JpaRepository<Species,Long> {

    public List<Species> findByPetShopId(Long id);

}
