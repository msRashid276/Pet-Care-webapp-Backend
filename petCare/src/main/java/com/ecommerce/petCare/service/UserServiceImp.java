package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.UserRepo;
import com.ecommerce.petCare.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public RegisterRequest register(RegisterRequest request) {

        try {
            Users user = Users.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            userRepo.save(user);
            return request;

        }catch (Exception e){
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }


    }
}
