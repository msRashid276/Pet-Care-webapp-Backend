package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreatePetRequest;
import com.ecommerce.petCare.request.CreatePetShopRequest;
import com.ecommerce.petCare.response.MessageResponse;
import com.ecommerce.petCare.service.PetService;
import com.ecommerce.petCare.service.PetShopService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop-owner")
public class AdminPetController{

    @Autowired
    private UserService userService;

    @Autowired
    private PetShopService petShopService;

    @Autowired
    private PetService petService;



    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestPart("pet") CreatePetRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        PetShop petShop = petShopService.findPetShopById(request.getPetShopId());

        Pet pet = petService.createPet(request,petShop);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }


    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePetShop(@PathVariable Long petId, @RequestBody CreatePetRequest updatePetRequest, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Pet pet = petService.updatePet(petId,updatePetRequest);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @DeleteMapping("/pet/{petId}")
    public ResponseEntity<MessageResponse> deletePet(@PathVariable Long petId,@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        petService.deletePet(petId);

        MessageResponse response = new MessageResponse();
        response.setMessage("Pet deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);

    }


    @PutMapping("/pet/{petId}")
    public ResponseEntity<Pet> updateAvailableStatus(@PathVariable Long petId, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Pet pet = petService.updateAvailableStatus(petId);

        return new ResponseEntity<>(pet,HttpStatus.OK);

    }


    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        List<Pet> pet = petService.getAllPets();

        return new ResponseEntity<>(pet,HttpStatus.OK);

    }




}
