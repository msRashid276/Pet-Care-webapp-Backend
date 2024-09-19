package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreatePetRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PetService {

        public Pet createPet(CreatePetRequest request, PetShop petShop) throws Exception;

        public void deletePet(Long petId) throws Exception;

        List<Pet> searchPets(String keyword) throws Exception;

        public List<Pet> getPetShopPet(
                 Long petShopId,
                 String species,
                 String breed,
                 String gender
        );

        public Pet findPetById(Long petId) throws Exception;

        public Pet updateAvailableStatus(Long petId) throws Exception;


        public List<Pet> getAllPets() throws Exception;



}
