package com.Rent.Rent.repository;


import com.Rent.Rent.ProductCatagory;
import com.Rent.Rent.model.Category;
import com.Rent.Rent.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

       
          List<Product> findByCategory(Category category);
           List<Product> findByName(String name);
           Optional<Product> findById(Long Id);
}