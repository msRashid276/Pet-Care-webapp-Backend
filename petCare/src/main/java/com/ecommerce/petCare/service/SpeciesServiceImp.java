package com.ecommerce.petCare.service;

import com.ecommerce.petCare.model.Species;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.SpeciesRepo;
import com.ecommerce.petCare.request.CreateSpeciesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SpeciesServiceImp implements SpeciesService{


        @Autowired
        private SpeciesRepo speciesRepo;




    @Override
    public Species createSpecies(CreateSpeciesRequest request) throws Exception {
        Species species = new Species();
        species.setName(request.getName());
        return speciesRepo.save(species);
    }

    @Override
    public void deleteSpecies(Long speciesId) throws Exception {
        Species species = findSpeciesById(speciesId);
        speciesRepo.delete(species);
    }

    @Override
    public Species findSpeciesById(Long speciesId) throws Exception {

        Optional<Species> species = speciesRepo.findById(speciesId);
        if(species.isEmpty()){
            throw new Exception("Species is not found with this id"+speciesId);
        }
        return species.get();
    }

    @Override
    public List<Species> getAllSpecies() {
        return speciesRepo.findAll();
    }
}
