package com.example.springboot.repositories; 

import com.example.springboot.models.Product; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

@Repository 
public interface ProductRepository extends JpaRepository<Product, Long> { 
}