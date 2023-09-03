package com.Rent.Rent.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rent.Rent.model.Booking;
import com.Rent.Rent.model.Product;
import com.Rent.Rent.repository.BookingRepository;
import com.Rent.Rent.repository.ProductRepository;
import com.Rent.Rent.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

 import jakarta.*;

	    @RestController
	    @RequestMapping("/bookings")
	    public class BookingController {
	        private final ProductService productService;

	        public BookingController(ProductService productService) {
	            this.productService = productService;
	        }

	        @PostMapping("/{userId}/book/products/{productId}")
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
	    }

	    
	    
	    


