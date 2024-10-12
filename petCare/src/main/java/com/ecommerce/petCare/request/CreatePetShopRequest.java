package com.ecommerce.petCare.request;


import com.ecommerce.petCare.model.Address;
import com.ecommerce.petCare.model.ContactInformation;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.List;

@Data
public class CreatePetShopRequest {

    private String name;
    private String description;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;


}
