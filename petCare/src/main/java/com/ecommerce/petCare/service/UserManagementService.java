package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.RegisterRequest;

import java.util.List;

public interface UserManagementService {
    List<Users> getAllUsers();


    RegisterRequest updateUser(Long id, RegisterRequest request, Users user);

    void deleteUser(Long id,Users user);

    Users getUserById(Long id, Users user);
}
