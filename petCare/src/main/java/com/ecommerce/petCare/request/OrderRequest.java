package com.ecommerce.petCare.request;


import com.ecommerce.petCare.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long petShopId;

    private Address address;
}
