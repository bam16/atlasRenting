package com.Rent.Rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rent.Rent.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Additional methods for specific querying or operations if needed
}