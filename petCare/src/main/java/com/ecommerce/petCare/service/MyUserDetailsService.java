package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.USER_ROLE;
import com.ecommerce.petCare.model.UserPrinciple;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = userRepo.findByEmail(username);

        if(usersOptional.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }

        Users user = usersOptional.get();

        USER_ROLE role = user.getRole();
        if(role==null){
            user.setRole(USER_ROLE.CUSTOMER);
        }

        return new UserPrinciple(user);
    }
}
