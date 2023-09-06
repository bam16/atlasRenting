package com.Rent.Rent.model;

import java.util.List;

import com.Rent.Rent.ProductCatagory;
import com.Rent.Rent.Role;

import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private String location;
    @Column
    private Long date;
    

        @ManyToOne
        @JoinColumn(name = "category_id")
        private Category category;

      
	@Enumerated(EnumType.STRING)
    private ProductCatagory productcatagory;
private boolean booked;
//Product entity class






    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Other properties, constructors, getters, and setters

    // Other methods



	public Product() {
    }

    public Product(String name, String description, double price,String location,Long date, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category=category;
        this.date=date;
    }
    
    public boolean isbooked() {
        return booked;
    }

    public void setbooked(boolean booked) {
        this.booked = booked;
    }

    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   

     public Category getcategory() {
	    return category;
}

    public void setcategory(Category category) {
	   this.category = category;
}
    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

    
}




