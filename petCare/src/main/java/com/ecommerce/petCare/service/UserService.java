package com.ecommerce.petCare.service;

import com.ecommerce.petCare.request.AuthenticationRequest;
import com.ecommerce.petCare.request.RegisterRequest;
import com.ecommerce.petCare.response.AuthenticationResponse;

public interface UserService {

    public RegisterRequest register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
