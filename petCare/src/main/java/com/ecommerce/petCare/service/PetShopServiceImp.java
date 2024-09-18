package com.ecommerce.petCare.service;


import com.ecommerce.petCare.dto.PetShopFavouritesDto;
import com.ecommerce.petCare.model.Address;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.AddressRepo;
import com.ecommerce.petCare.repository.PetShopRepo;
import com.ecommerce.petCare.repository.UserRepo;
import com.ecommerce.petCare.request.CreatePetShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PetShopServiceImp implements PetShopService{



        @Autowired
        private AddressRepo addressRepo;

        @Autowired
        private PetShopRepo petShopRepo;

        @Autowired
        private PetShopFavouritesDto petShopFavouritesDto;

        @Autowired
        private UserRepo userRepo;




    @Override
    public PetShop createPetShop(CreatePetShopRequest request, Users user) throws Exception {

        Address address = addressRepo.save(request.getAddress());

        PetShop petShop = new PetShop();

        petShop.setName(request.getName());
        petShop.setAddress(address);
        petShop.setDescription(request.getDescription());
        petShop.setImages(request.getImages());
        petShop.setContactInformation(request.getContactInformation());
        petShop.setOpeningHours(request.getOpeningHours());
        petShop.setRegistrationDate(LocalDateTime.now());
        petShop.setOwner(user);

        return petShopRepo.save(petShop);
    }

    @Override
    public PetShop updatePetShop(Long petShopId, CreatePetShopRequest updatePetShopRequest) throws Exception {

        PetShop petShop = findPetShopById(petShopId);

        if(updatePetShopRequest.getAddress()!=null){
            Address updatedAddress = addressRepo.save(updatePetShopRequest.getAddress());
            petShop.setAddress(updatedAddress);
        }

        if(updatePetShopRequest.getName()!=null){
            petShop.setName(updatePetShopRequest.getName());
        }

        if(updatePetShopRequest.getDescription()!=null){
            petShop.setDescription(updatePetShopRequest.getDescription());
        }

        if(updatePetShopRequest.getContactInformation()!=null){
            petShop.setContactInformation(updatePetShopRequest.getContactInformation());
        }

        return petShopRepo.save(petShop);
    }


    @Override
    public void deletePetShop(Long petShopId) throws Exception {

        PetShop petShop = findPetShopById(petShopId);
        petShopRepo.delete(petShop);

    }


    @Override
    public PetShop findPetShopById(Long petShopId) throws Exception {

        Optional<PetShop> petShop = petShopRepo.findById(petShopId);

        if(petShop.isEmpty()){
            throw new Exception("PetShop is not found with this id"+petShopId);
        }
        return petShop.get();

    }


    @Override
    public PetShop getPetShopByUserId(Long userId) throws Exception {

        PetShop petShop = petShopRepo.findByOwnerId(userId);

        if(petShop==null){
            throw new Exception("PetShop is not found with owner id " + userId);
        }
        return petShop;
    }



    @Override
    public PetShopFavouritesDto addToFavourites(Long petShopId, Users user) throws Exception {

        PetShop petShop = findPetShopById(petShopId);

        petShopFavouritesDto.setDescription(petShop.getDescription());
        petShopFavouritesDto.setImages(petShop.getImages());
        petShopFavouritesDto.setTitle(petShop.getName());
        petShopFavouritesDto.setId(petShopId);


        boolean isFavourited = false;

        List<PetShopFavouritesDto> favourites = user.getFavourites();

        for (PetShopFavouritesDto favourite: favourites){
            if(favourite.getId().equals(petShopId)){
                isFavourited=true;
                break;
            }
        }

        if (isFavourited){
            favourites.removeIf(favourite->favourite.getId().equals(petShopId));
        }else {
            favourites.add(petShopFavouritesDto);
        }

        userRepo.save(user);

        return petShopFavouritesDto;
    }


    @Override
    public PetShop updatePetShopStatus(Long petShopId) throws Exception {

        PetShop petShop = findPetShopById(petShopId);

        petShop.setOpen(!petShop.isOpen());
        return petShopRepo.save(petShop);

    }



    @Override
    public List<PetShop> getAllPetShops() {
        return petShopRepo.findAll();
    }

    @Override
    public List<PetShop> searchPetShop(String keyword) {
        return petShopRepo.findPetShopBySearchQuery(keyword);
    }

}
