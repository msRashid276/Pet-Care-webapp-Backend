package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.AuthenticationRequest;
import com.ecommerce.petCare.request.RegisterRequest;
import com.ecommerce.petCare.response.AuthenticationResponse;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterRequest request){
        Users register = userService.register(request);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        AuthenticationResponse authenticate = userService.authenticate(request);
        return new ResponseEntity<>(authenticate, HttpStatus.OK);
    }

}
