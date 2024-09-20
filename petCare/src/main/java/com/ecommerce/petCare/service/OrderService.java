package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Order;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.OrderRequest;

import java.util.List;

public interface OrderService {

        public Order createOrder(OrderRequest request, Users user) throws Exception;

        public Order updateOrder(Long orderId, String orderStatus) throws Exception;

        public void cancelOrder(Long orderId) throws Exception;

        public List<Order> getUserOrders(Long userId) throws Exception;

        public List<Order> getPetShopOrders(Long petShopId, String orderStatus) throws Exception;

        public Order findOrderById(Long orderId) throws Exception;

}
