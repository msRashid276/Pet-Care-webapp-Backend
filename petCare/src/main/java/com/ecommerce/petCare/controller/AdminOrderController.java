package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.model.Order;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.service.OrderService;
import com.ecommerce.petCare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping("/res_owner/order/{restaurantId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long restaurantId, @RequestParam(required = false) String order_status, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Order> order = orderService.getPetShopOrders(user.getId(),order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/res_owner/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                   @PathVariable String orderStatus,
                                                   @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Order order = orderService.updateOrder(orderId,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
