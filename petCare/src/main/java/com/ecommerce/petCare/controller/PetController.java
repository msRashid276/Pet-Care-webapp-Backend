package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.service.PetService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/pets")
public class PetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;


    @GetMapping("/search")
    public ResponseEntity<List<Pet>> searchPets(@RequestParam String keyword, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Pet> pets = petService.searchPets(keyword);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/pet-shop/{petShopId}")
    public ResponseEntity <List<Pet>> getPetShopPets(@PathVariable Long petShopId,
                                                          @RequestParam(required = false) String species,
                                                          @RequestParam(required = false) String breed,
                                                          @RequestParam(required = false) String gender,
                                                          @RequestHeader("Authorization") String authHeader
    ) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Pet> pets = petService.getPetShopPet(petShopId,species,breed,gender);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }


}
