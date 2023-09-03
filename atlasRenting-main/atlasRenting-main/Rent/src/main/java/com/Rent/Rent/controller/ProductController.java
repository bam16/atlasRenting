package com.Rent.Rent.controller;

import com.Rent.Rent.ProductCatagory;
import com.Rent.Rent.Role;
import com.Rent.Rent.model.Booking;
import com.Rent.Rent.model.Product;
import com.Rent.Rent.model.User;
import com.Rent.Rent.repository.ProductRepository;
import com.Rent.Rent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
        
        
    }
    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable ProductCatagory productcatagory) {
        List<Product> products = productService.getAllProductsByCategory(productcatagory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/name/{name}")
    public ResponseEntity<List<Product>> getAllProductsByName(@PathVariable String name) {
        List<Product> products = productService.getAllProductsByName(name);
        return ResponseEntity.ok(products);
    }
    
    @PostMapping("/products/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
        
        }
  
    
    
    
    @PostMapping("/users/{userId}/createproduct/")
      public ResponseEntity<Product> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String location,
            @RequestParam Long Date,
            @RequestParam ProductCatagory productcatagory
    ) {
       Product product = productService.createProduct(name,description,price,location,Date,productcatagory);
        return ResponseEntity.ok(product);
    }

    
    @PutMapping("/users/{userId}/updateproduct/id/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long userId,
            @PathVariable Long id,
            @RequestParam String newName,
            @RequestParam double newPrice,
            @RequestParam Long newDate,
            @RequestParam ProductCatagory newProductCatagory
    ) {
        Product updatedProduct = productService.updateProduct(userId, id, newName, newPrice, newDate, newProductCatagory);
        
        if (updatedProduct != null) {
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
    @DeleteMapping("/users/{userId}/deleteproduct/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long Id) {
        ResponseEntity<String> response = productService.deleteProduct(Id);
        return response;
    }
    @PutMapping("/users/{userId}/updateproductstatus/id/{id}")
    public ResponseEntity<String> updateProductStatus(
            @PathVariable Long Id,
            @RequestParam boolean rented
    ) {
        productService.updateProductRentStatus(Id, rented);
        return ResponseEntity.ok("product status updated");
    }
    

    		 @PostMapping("/cart/{cartId}/products/{productId}")
    		 public void addToCart(@PathVariable Long cartId, @PathVariable Long productId) {
    		     productService.addToCart(productId, cartId);
    		 }
    		}

    
    
}
    

