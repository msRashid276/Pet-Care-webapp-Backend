package com.ecommerce.petCare.controller;


import com.ecommerce.petCare.service.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary/upload")
public class CloudinaryImageUploadController {

    @Autowired
    private CloudinaryImageService cloudinaryImageService;

    @PostMapping()
    public ResponseEntity<List<Map<String,Object>>> uploadImages(@RequestParam("shopImageFile")MultipartFile[] files){
        System.out.println(Arrays.toString(files));
        List<Map<String,Object>> data = this.cloudinaryImageService.uploadFiles(files);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
