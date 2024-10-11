package com.ecommerce.petCare.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap<>();
        config.put("cloud_name", "doex7hxfc");
        config.put("api_key", "615659969592675");
        config.put("api_secret", "8ZH2AmaU6Xsu0jeu63WHgghgP6k");
        config.put("secure", true);

        return new Cloudinary(config);

    }

}
