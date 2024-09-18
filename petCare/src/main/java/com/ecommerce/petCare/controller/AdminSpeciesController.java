package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Species;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreateSpeciesRequest;
import com.ecommerce.petCare.response.MessageResponse;
import com.ecommerce.petCare.service.SpeciesService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop-owner/species")
public class AdminSpeciesController {

        @Autowired
        private SpeciesService speciesService;

        @Autowired
        private UserService userService;


    @PostMapping()
    public ResponseEntity<Species> createSpecies(@RequestBody CreateSpeciesRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Species species = speciesService.createSpecies(request);

        return new ResponseEntity<>(species, HttpStatus.CREATED);
    }

    @DeleteMapping("/{speciesId}")
    public ResponseEntity<MessageResponse> deleteSpecies(@PathVariable Long speciesId, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        speciesService.deleteSpecies(speciesId);

        MessageResponse response = new MessageResponse();
        response.setMessage("species deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{speciesId}")
    public ResponseEntity<Species> findSpeciesById(@PathVariable Long speciesId,@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Species species = speciesService.findSpeciesById(speciesId);
        return new ResponseEntity<>(species, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Species>> getAllSpecies(@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Species> species = speciesService.getAllSpecies();
        return new ResponseEntity<>(species, HttpStatus.OK);
    }



}
