package com.Rent.Rent.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Rent.Rent.model.Cart;
import com.Rent.Rent.model.Booking;
import com.Rent.Rent.model.Product;
import com.Rent.Rent.model.User;
import com.Rent.Rent.repository.BookingRepository;
import com.Rent.Rent.repository.CartRepository;
import com.Rent.Rent.repository.ProductRepository;
import com.Rent.Rent.repository.UserRepository;
import com.Rent.Rent.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

 import jakarta.*;

	    @RestController
	    @RequestMapping("/bookings")
	    public class BookingController {
	        private final ProductService productService;

	        public BookingController(ProductService productService,CartRepository cartRepository,UserRepository userrepository) {
	            this.productService = productService;
	           
	        }

	        @PostMapping("/user/{userId}/book/products/{productId}")
	        public ResponseEntity<?> bookProduct(@PathVariable Long userId,
	                                             @PathVariable Long Id,
	                                             @RequestBody Booking booking) {
	            try {
	                Booking booked = productService.bookProduct(userId, Id, booking);
	                return ResponseEntity.ok(booked);
	            } catch (EntityNotFoundException e) {
	                            return ResponseEntity.notFound().build();
	            } catch (IllegalStateException e) {
	                return ResponseEntity.badRequest().body(e.getMessage());
	            }
	            
	            
	        }	            
	           

	                @PostMapping("/add-to-cart/{userId}/{productId}")
	                public ResponseEntity<String> addToCart(@PathVariable Long userId, @PathVariable Long productId) {
	                    try {
	                        productService.addProductToCart(userId, productId);
	                        return ResponseEntity.ok("Product added to cart");
	                    } catch (EntityNotFoundException e) {
	                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	                    } catch (IllegalArgumentException e) {
	                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	                    }
	                }

	                @PostMapping("/remove-from-cart/{userId}/{productId}")
	                public ResponseEntity<String> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
	                    try {
	                        productService.removeProductFromCart(userId, productId);
	                        return ResponseEntity.ok("Product removed from cart");
	                    } catch (EntityNotFoundException e) {
	                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	                    } catch (IllegalArgumentException e) {
	                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	                    }
	                }

	                @PostMapping("/checkout/{userId}")
	                public ResponseEntity<String> checkoutCart(@PathVariable Long userId) {
	                    try {
	                        productService.checkoutCart(userId);
	                        return ResponseEntity.ok("Cart successfully checked out");
	                    } catch (EntityNotFoundException e) {
	                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	                    } catch (IllegalStateException e) {
	                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	                    }
	                }

	               
	            }
	       

	        
	    	    
	    
	    


