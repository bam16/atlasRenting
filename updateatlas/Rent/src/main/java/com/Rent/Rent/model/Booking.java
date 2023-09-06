package com.Rent.Rent.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import jakarta.persistence.*;

@Entity
	public class Booking {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long bookingid;

	   
	
		@ManyToOne(fetch = FetchType.LAZY)
	    private  User user;
	    @ManyToOne(fetch = FetchType.LAZY)
	    
	    private Product product;

	    private LocalDate startDate;
	    private LocalDate endDate;
        private double totalPrice;
	    
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public Long getBookingid() {
			return bookingid;
		}
		public void setBookingid(Long bookingid) {
			this.bookingid = bookingid;
		}
	    // Constructors, getters, and setters
		public void setTotalPrice(double totalPrice) {
			this.totalPrice=totalPrice;
			
		}
		
			
		}
	

