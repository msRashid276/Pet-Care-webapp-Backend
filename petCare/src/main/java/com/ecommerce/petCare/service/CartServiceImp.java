package com.ecommerce.petCare.service;


import com.ecommerce.petCare.model.Cart;
import com.ecommerce.petCare.model.CartItem;
import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.Users;
import com.ecommerce.petCare.repository.CartItemRepo;
import com.ecommerce.petCare.repository.CartRepo;
import com.ecommerce.petCare.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private PetService petService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest request, Users user) throws Exception {

        Pet pet = petService.findPetById(request.petId);
        Cart cart = cartRepo.findByCustomerId(user.getId());

        for(CartItem cartItem: cart.getItem()){
            if(cartItem.getPet().equals(pet)){
                int newQuantity = cartItem.getQuantity()+request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setPet(pet);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setTotalPrice(request.getQuantity()*pet.getPrice());

        CartItem savedCartItem = cartItemRepo.save(newCartItem);
        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found with this id");
        }

        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getPet().getPrice()*quantity);

        return cartItemRepo.save(item);

    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, Users user) throws Exception {
        Cart cart = cartRepo.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found with this id");
        }

        CartItem item = cartItem.get();
        cart.getItem().remove(item);
        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem:cart.getItem()){
            total+= cartItem.getPet().getPrice()* cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepo.findById(id);
        if(cart.isEmpty()){
            throw new Exception("Cart is not found by this id");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepo.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);

        cart.getItem().clear();
        return cartRepo.save(cart);
    }
}
