package com.Rent.Rent.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
   

	private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
 // Category entity class
    
 

        public Category() {
        }

        public Category(Long category_id,String name) {
            this.name = name;
            this.category_id=category_id;
        }

        

            // Getters and setters
        public Long getCategory_id() {
    		return category_id;
    	}

    	public void setCategory_id(Long category_id) {
    		this.category_id = category_id;
    	}
           

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Product> getProducts() {
                return products;
            }

            public void setProducts(List<Product> products) {
                this.products = products;
            }
        }

        // ...
   

