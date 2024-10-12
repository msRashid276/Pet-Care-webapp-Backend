package com.ecommerce.petCare.service;

import com.ecommerce.petCare.model.PetShop;
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

        @Autowired
        private PetShopService petShopService;



    @Override
    public Species createSpecies(String name, Long userId) throws Exception {


        try {
            PetShop petShop = petShopService.getPetShopByUserId(userId);

            Optional<Species> existingSpecies = speciesRepo.findByName(name);
            if(existingSpecies.isPresent()){
                throw new Exception("Species already exists in this pet shop");
            }

            Species species = new Species();
            species.setName(name);
            species.setPetShop(petShop);

            return speciesRepo.save(species);
        }catch (Exception e){
            System.err.println("Error creating species: " + e.getMessage());
            throw new Exception("Failed to create species", e);
        }
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

    @Override
    public List<Species> findSpeciesByPetShopIdFromUserId(Long userId) throws Exception {

        PetShop petShop = petShopService.getPetShopByUserId(userId);
        return speciesRepo.findByPetShopId(petShop.getId());
    }
}
