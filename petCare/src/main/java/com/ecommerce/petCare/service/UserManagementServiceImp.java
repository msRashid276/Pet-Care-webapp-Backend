package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.UserRepo;
import com.ecommerce.petCare.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementServiceImp implements UserManagementService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Users createUser(RegisterRequest request, Users user) throws Exception {

        Users newUser = new Users();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepo.save(newUser);
        return newUser;
    }

    @Override
    public Users updateUser(Long id, RegisterRequest request, Users user) {


        try {
            Optional<Users> isItExistingUser = userRepo.findById(id);
            if (isItExistingUser.isPresent()) {

                Users existingUser = isItExistingUser.get();
                existingUser.setFirstName(request.getFirstName());
                existingUser.setLastName(request.getLastName());
                existingUser.setEmail(request.getEmail());
                if (!request.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
                }
                existingUser.setRole(request.getRole());
                userRepo.save(existingUser);
                return existingUser;
            } else {
                throw new RuntimeException("User not found");
            }
        }catch (Exception e){
            throw new RuntimeException("updation failed"+e.getMessage());
        }


    }



    @Override
    public void deleteUser(Long id, Users user) {
        userRepo.deleteById(id);
    }


    @Override
    public Users getUserById(Long id, Users user) {
         return userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    }


}
