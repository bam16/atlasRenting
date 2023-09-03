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
    private Long id;
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
    
   
	@Enumerated(EnumType.STRING)
    private ProductCatagory productcatagory;
private boolean booked;
//Product entity class

 @ManyToMany(mappedBy = "products")
 private List<Cart> carts;

 public void addCart(Cart cart) {
     carts.add(cart);
     cart.getProducts().add(this);
 }

 // ...


	public Product() {
    }

    public Product(String name, String description, double price,String location,Long date, ProductCatagory productcatagoty) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productcatagory=productcatagoty;
        this.date=date;
    }
    
    public boolean isbooked() {
        return booked;
    }

    public void setbooked(boolean booked) {
        this.booked = booked;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
   

     public ProductCatagory getProductcatagory() {
	    return productcatagory;
}

    public void setProductcatagory(ProductCatagory productcatagory) {
	   this.productcatagory = productcatagory;
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


