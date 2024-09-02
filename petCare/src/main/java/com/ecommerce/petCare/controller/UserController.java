package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.RegisterRequest;
import com.ecommerce.petCare.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(RegisterRequest request){
        RegisterRequest register = userService.register(request);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<RegisterRequest> register(RegisterRequest request){
        RegisterRequest register = userService.register(request);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

}
