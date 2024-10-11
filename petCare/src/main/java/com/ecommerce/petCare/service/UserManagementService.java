package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.RegisterRequest;

import java.util.List;
import java.util.Optional;

public interface UserManagementService {
    List<Users> getAllUsers();

    Users createUser(RegisterRequest request, Users user) throws Exception;

    Users updateUser(Long id, RegisterRequest request, Users user);

    void deleteUser(Long id,Users user);

    Users getUserById(Long id, Users user);

    List<Users> searchUsers(String keyword);
}
