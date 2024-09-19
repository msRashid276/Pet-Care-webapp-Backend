package com.ecommerce.petCare.repository;

import com.ecommerce.petCare.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
}
