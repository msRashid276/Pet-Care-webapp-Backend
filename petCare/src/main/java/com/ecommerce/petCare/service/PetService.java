package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreatePetRequest;

public interface PetService {

        public Pet createPet(CreatePetRequest request, Users user) throws Exception;

        public void deletePet(Long petId) throws Exception;

}
