package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Species;
import com.ecommerce.petCare.request.CreateSpeciesRequest;


import java.util.List;

public interface SpeciesService {

    public Species createSpecies(String name,Long userId) throws Exception;

    public void deleteSpecies(Long speciesId) throws Exception;

    public Species findSpeciesById(Long speciesId) throws Exception;

    public List<Species> getAllSpecies();

    public List<Species> findSpeciesByPetShopIdFromUserId(Long userId) throws Exception;



}
