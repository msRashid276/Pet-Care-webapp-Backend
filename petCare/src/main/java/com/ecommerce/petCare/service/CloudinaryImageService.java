package com.ecommerce.petCare.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CloudinaryImageService {
    public List<Map<String,Object>> uploadFiles(MultipartFile[] file);
}
