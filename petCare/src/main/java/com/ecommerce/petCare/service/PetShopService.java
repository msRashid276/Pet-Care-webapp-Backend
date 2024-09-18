package com.ecommerce.petCare.service;


import com.ecommerce.petCare.dto.PetShopFavouritesDto;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreatePetShopRequest;

import java.util.List;

public interface PetShopService {

    public PetShop createPetShop(CreatePetShopRequest request, Users user) throws Exception;

    public PetShop updatePetShop(Long petShopId, CreatePetShopRequest updatePetShopRequest) throws Exception;

    public void deletePetShop(Long petShopId) throws Exception;

    public PetShop findPetShopById(Long petShopId) throws Exception;

    public PetShop getPetShopByUserId(Long userId) throws Exception;

    public PetShop updatePetShopStatus(Long petShopId) throws Exception;

    public PetShopFavouritesDto addToFavourites(Long petShopId, Users user) throws Exception;

    public List<PetShop> getAllPetShops();

    public List<PetShop> searchPetShop(String keyword);

}


