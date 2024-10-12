package com.ecommerce.petCare.request;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreatePetRequest {

    private String name;
    private Long speciesId;
    private String breed;
    private int age;
    private String gender;
    private Long price;
    private List<String> images;
    private String description;
    private Long petShopId;
    private boolean available;

}
