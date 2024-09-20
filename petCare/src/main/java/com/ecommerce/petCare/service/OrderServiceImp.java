package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.*;
import com.ecommerce.petCare.repository.AddressRepo;
import com.ecommerce.petCare.repository.OrderItemRepo;
import com.ecommerce.petCare.repository.OrderRepo;
import com.ecommerce.petCare.repository.UserRepo;
import com.ecommerce.petCare.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{

        @Autowired
        private AddressRepo addressRepo;

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private PetShopService petShopService;

        @Autowired
        private CartService cartService;

        @Autowired
        private OrderItemRepo orderItemRepo;

        @Autowired
        private OrderRepo orderRepo;

    @Override
    public Order createOrder(OrderRequest request, Users user) throws Exception {

        Address shippingAddress = request.getAddress();
        Address savedAddress = addressRepo.save(shippingAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepo.save(user);
        }

        PetShop petShop = petShopService.findPetShopById(request.getPetShopId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setAddress(savedAddress);
        createdOrder.setPetShop(petShop);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItemList = new ArrayList<>();

        for(CartItem cartItem : cart.getItem()){
            OrderItem orderItem = new OrderItem();
            orderItem.setPet(cartItem.getPet());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepo.save(orderItem);
            orderItemList.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setOrderItems(orderItemList);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepo.save(createdOrder);
        petShop.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);

        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")
        ){
            order.setOrderStatus(orderStatus);
            return orderRepo.save(order);

        }
        throw new Exception("please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepo.findByCustomerId(userId);
    }

    @Override
    public List<Order> getPetShopOrders(Long petShopId, String orderStatus) throws Exception {
        List<Order> orders = orderRepo.findByPetShopId(petShopId);

        if(orderStatus!=null){
            orders = orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepo.findById(orderId);
        if(order.isEmpty()){
            throw new Exception("Order not found with this id");
        }

        return order.get();
    }
}
