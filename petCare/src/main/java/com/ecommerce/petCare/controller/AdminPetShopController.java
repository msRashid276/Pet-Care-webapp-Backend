package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.CreatePetShopRequest;
import com.ecommerce.petCare.response.MessageResponse;
import com.ecommerce.petCare.service.PetShopService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop-owner/pet-shop")
public class AdminPetShopController {


        @Autowired
        private PetShopService petShopService;

        @Autowired
        private UserService userService;




    @PostMapping()
    public ResponseEntity<PetShop> createPetShop(@RequestBody CreatePetShopRequest request, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShop petShop = petShopService.createPetShop(request,user);
        return new ResponseEntity<>(petShop, HttpStatus.CREATED);
    }


    @PutMapping("/{petShopId}")
    public ResponseEntity<PetShop> updatePetShop(@PathVariable Long petShopId,@RequestBody CreatePetShopRequest updatePetShopRequest, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShop petShop = petShopService.updatePetShop(petShopId,updatePetShopRequest);
        return new ResponseEntity<>(petShop, HttpStatus.CREATED);
    }

    @DeleteMapping("/{petShopId}")
    public ResponseEntity<MessageResponse> deletePetShop(@PathVariable Long petShopId, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        petShopService.deletePetShop(petShopId);

        MessageResponse response = new MessageResponse();
        response.setMessage("PetShop deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{petShopId}/status")
    public ResponseEntity<PetShop> updatePetShopStatus(@PathVariable Long petShopId, @RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShop petShop = petShopService.updatePetShopStatus(petShopId);
        return new ResponseEntity<>(petShop, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<PetShop> findPetShopByUserId(@RequestHeader("Authorization") String authHeader) throws Exception{

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        PetShop petShop = petShopService.getPetShopByUserId(user.getId());
        return new ResponseEntity<>(petShop, HttpStatus.OK);
    }


}
