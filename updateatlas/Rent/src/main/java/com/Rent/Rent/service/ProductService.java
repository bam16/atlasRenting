package com.Rent.Rent.service;

import com.Rent.Rent.ProductCatagory;
import com.Rent.Rent.Role;
import com.Rent.Rent.model.Booking;
import com.Rent.Rent.model.Cart;
import com.Rent.Rent.model.Category;
import com.Rent.Rent.model.Product;
import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.model.Renter;
import com.Rent.Rent.model.User;
import com.Rent.Rent.repository.BookingRepository;
import com.Rent.Rent.repository.CartRepository;
import com.Rent.Rent.repository.ProductRepository;
import com.Rent.Rent.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BookingRepository bookingRepository;
	private final CartRepository cartRepository;
@Autowired
    public ProductService(UserRepository userRepository, ProductRepository productRepository, BookingRepository bookingRepository ,CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.bookingRepository = bookingRepository;
        this.cartRepository=cartRepository;
    }
    

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
   

    public ResponseEntity<String> getProduct(Long Id) {
        Optional<Product> productOptional = productRepository.findById(Id);
        if (productOptional.isPresent()) {
            userRepository.deleteById(Id);
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    public List<Product> getAllProductsByName(String name) {
        return this.productRepository.findByName(name);
    }
    
    public List<Product> getAllProductsByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }
    
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
    
    
  
    public Product createProduct(String name, String description, double price,String location,Long date, Category category) {
        Product product = new Product(name,description ,price,location,date,category);
        return productRepository.save(product);
    }

    public void updateProuctCatagory(Long Id, Category category) {
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("product not found"));
        product.setcategory(category);
        productRepository.save(product);
    }

   
   

 
    public Product updateProduct(Long userId,Long Id, String newName, double newPrice,Long newDate, Category newCategory) {
            Product product = productRepository.findById(Id)
                    .orElseThrow(() -> new EntityNotFoundException("product not found"));

            // Update user properties
            product.setName(newName);
            product.setPrice(newPrice);
            product.setcategory(newCategory);
            product.setDate(newDate);

            // Save the updated product
            return productRepository.save(product);
        }


        public ResponseEntity<String> deleteProduct(Long Id) {
            Optional<Product> productOptional = productRepository.findById(Id);
            if (productOptional.isPresent()) {
                productRepository.deleteById(Id);
                return ResponseEntity.ok("product is deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
            }
        }
        public void updateProductRentStatus(Long Id, boolean booked) {
            Product product = productRepository.findById(Id)
                    .orElseThrow(() -> new EntityNotFoundException("product not found"));
            product.setbooked(booked);
            productRepository.save(product);
        }
    
      
       
   

            public Booking bookProduct(Long userId, Long Id, Booking booking) {
                Product product = productRepository.findById(Id)
                        .orElseThrow(() -> new EntityNotFoundException("Product not found."));

                if (product.isbooked()) {
                    throw new IllegalStateException("Product is already booked.");
                }

                LocalDate startDate = booking.getStartDate();
                LocalDate endDate = booking.getEndDate();

                long duration = ChronoUnit.DAYS.between(startDate, endDate);
                double totalPrice = product.getPrice() * duration;

                booking.setUser(null);
                booking.setProduct(product);
                booking.setTotalPrice(totalPrice);

                bookingRepository.save(booking);

                product.setbooked(true);
                
                productRepository.save(product);

                return booking;
            }
        
        
             public String formatPrice(double price) {
                 // You can implement your own logic here to format the price as needed
                 return "$" + price;
             }
             
             
             public Cart addProductToCart(Long userId, Long Id) {
            	    User user = userRepository.findById(userId)
            	            .orElseThrow(() -> new EntityNotFoundException("User not found"));

            	    Product product = productRepository.findById(Id)
            	            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            	    Cart cart = user.getCart();
            	    if (cart == null) {
            	        cart = new Cart();
            	        user.setCart(cart);
            	    }

            	    cart.getProducts().add(product);

            	    return userRepository.save(user).getCart();
            	}
            
                 public Cart removeProductFromCart(Long userId, Long Id) {
                     User user = userRepository.findById(userId)
                             .orElseThrow(() -> new EntityNotFoundException("User not found"));

                     Product product = productRepository.findById(Id)
                             .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                     Cart cart = user.getCart();
                     if (cart == null) {
                         throw new IllegalStateException("Cart not found for the user");
                     }

                     cart.removeProduct(product);

                     return userRepository.save(user).getCart();
                 }

                 // Other methods
                 
  

                     public void checkoutCart(Long userId) {
                         User user = userRepository.findById(userId)
                                 .orElseThrow(() -> new EntityNotFoundException("User not found"));

                         Cart cart = user.getCart();
                         if (cart == null) {
                             throw new IllegalStateException("Cart not found for the user");
                         }

                         Set<Product> products = cart.getProducts();
                         if (products.isEmpty()) {
                             throw new IllegalStateException("Cart is empty");
                         }

                         // Perform the booking process for each product
                         for (Product product : products) {
                             bookProduct(product, user);
                         }

                         // Clear the cart after successful booking
                         cart.getProducts().clear();

                         userRepository.save(user);
                     }

                     private void bookProduct(Product product, User user) {
                         // Create a booking entry for the product
                         Booking booking = new Booking();
                         booking.setUser(user);
                         booking.setProduct(product);
                         // Set other booking properties as needed
                         // For example, booking date, status, etc.

                         // Save the booking entry to the database
                         bookingRepository.save(booking);

                       
                 
                     }
                     }
             

