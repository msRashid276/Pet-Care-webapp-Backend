package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.UserPrinciple;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.UserRepo;
import com.ecommerce.petCare.request.AuthenticationRequest;
import com.ecommerce.petCare.request.RegisterRequest;
import com.ecommerce.petCare.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


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



    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request);

        try{
//            UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getEmail());
//
//            if(userDetails==null){
//                throw new RuntimeException("Invalid username");
//            }
//
//            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
//                throw new RuntimeException("Invalid password");
//            }


            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            System.out.println("yooooooooo");
            if(authentication.isAuthenticated()){

                var userPrinciple = (UserPrinciple) authentication.getPrincipal();
                var jwtToken = jwtService.generateToken(authentication);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .firstName(userPrinciple.getUser().getFirstName())
                        .role(String.valueOf(userPrinciple.getUser().getRole()))
                        .build();
            }else{
                throw new RuntimeException("Authentication failed");
            }
        }catch (Exception e){
            throw new RuntimeException("Invalid credentials");
        }


    }
}
