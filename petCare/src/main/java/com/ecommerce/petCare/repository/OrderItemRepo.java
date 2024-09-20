package com.ecommerce.petCare.repository;


import com.ecommerce.petCare.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {


}
