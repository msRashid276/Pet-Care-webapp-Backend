package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Order;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.OrderRequest;
import com.ecommerce.petCare.response.PaymentResponse;
import com.ecommerce.petCare.service.OrderService;
import com.ecommerce.petCare.service.PaymentService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Order order = orderService.createOrder(request,user);

        PaymentResponse paymentResponse = paymentService.createPaymentLink(order);

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }


    @GetMapping("order")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
