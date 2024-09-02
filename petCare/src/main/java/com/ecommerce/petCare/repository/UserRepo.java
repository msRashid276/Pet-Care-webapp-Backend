package com.ecommerce.petCare.repository;

import com.ecommerce.petCare.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {


}
