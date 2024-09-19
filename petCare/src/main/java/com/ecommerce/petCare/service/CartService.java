package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Cart;
import com.ecommerce.petCare.model.CartItem;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, Users user) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, Users user) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

}
