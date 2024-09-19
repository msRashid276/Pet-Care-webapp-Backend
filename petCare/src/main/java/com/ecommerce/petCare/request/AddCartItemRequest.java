package com.ecommerce.petCare.request;


import lombok.Data;

@Data
public class AddCartItemRequest {

    public Long petId;

    private int quantity;

}
