package com.ecommerce.petCare.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryImageServiceImp implements CloudinaryImageService{

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Map<String,Object>> uploadFiles(MultipartFile[] files) {

        List<Map<String, Object>> uploadedFilesData = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map<String, Object> data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
                System.out.println(data+"data");
                uploadedFilesData.add(data);
            } catch (IOException e) {
                throw new RuntimeException("Image uploading failed for " + file.getOriginalFilename() + ": " + e.getMessage());
            }
        }
        System.out.println(uploadedFilesData);
        return uploadedFilesData;
    }
}
