package com.Rent.Rent.model;

import java.util.List;

import jakarta.persistence.*;
//Cart entity class
//Cart entity class
@Entity
public class Cart {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long cartId;
 @SuppressWarnings("unused")
private Long userId;
 
 @ManyToMany
 @JoinTable(
     name = "cart_product",
     joinColumns = @JoinColumn(name = "cart_id"),
     inverseJoinColumns = @JoinColumn(name = "id")
 )
 private List<Product> products;
 public List<Product>getProducts(){
	 return products;
 }
 // Constructors, getters, and setters
}

//Product entity class
