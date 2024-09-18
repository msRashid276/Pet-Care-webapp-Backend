package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.dto.PetShopFavouritesDto;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.service.PetShopService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/pet-shops")
public class PetShopController {

        @Autowired
        private PetShopService petShopService;

        @Autowired
        private UserService userService;


    @GetMapping()
    public ResponseEntity<List<PetShop>> getAllPetShops(@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<PetShop> petShops = petShopService.getAllPetShops();
        return new ResponseEntity<>(petShops, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<PetShop>> searchPetShop(@RequestParam String keyword,@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<PetShop> petShops = petShopService.searchPetShop(keyword);
        return new ResponseEntity<>(petShops, HttpStatus.OK);
    }


    @GetMapping("/{petShopId}")
    public ResponseEntity<PetShop> findPetShopById(@PathVariable Long petShopId,@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShop petShop = petShopService.findPetShopById(petShopId);
        return new ResponseEntity<>(petShop, HttpStatus.OK);
    }


    @PutMapping("/{petShopId}/add-favourites")
    public ResponseEntity<PetShopFavouritesDto> addToFavourites(@PathVariable Long petShopId, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShopFavouritesDto petShops = petShopService.addToFavourites(petShopId,user);
        return new ResponseEntity<>(petShops, HttpStatus.OK);
    }



}
