package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Order;
import com.ecommerce.petCare.response.PaymentResponse;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws Exception;

}
