package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.RegisterRequest;
import com.ecommerce.petCare.service.UserManagementService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminUserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(@RequestHeader("Authorization") String authHeader ) throws Exception {
        System.out.println(authHeader);

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        List<Users> customers = userManagementService.getAllUsers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Users> addUser(@RequestBody RegisterRequest request, @RequestHeader("Authorization") String authHeader ) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Users customers = userManagementService.createUser(request,user);
        return new ResponseEntity<>(customers, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody RegisterRequest request, @RequestHeader("Authorization") String authHeader ) throws Exception {


        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Users customers = userManagementService.updateUser(id,request,user);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String authHeader ) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
         userManagementService.deleteUser(id,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id, @RequestHeader("Authorization") String authHeader ) throws Exception {


        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Users customers = userManagementService.getUserById(id,user);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/users/search")
    public ResponseEntity<List<Users>> searchUsers(@RequestParam String keyword,@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Users> users = userManagementService.searchUsers(keyword);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }





}
