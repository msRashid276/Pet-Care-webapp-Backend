package com.ecommerce.petCare.repository;

import com.ecommerce.petCare.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {

    public Cart findByCustomerId(Long userId);

}
