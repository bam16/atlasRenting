package com.Rent.Rent.service;

import com.Rent.Rent.Role;
import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.model.Renter;
import com.Rent.Rent.model.User;
import com.Rent.Rent.repository.RentalOwnerRepository;
import com.Rent.Rent.repository.RenterRepository;
import com.Rent.Rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;


@Service
@Transactional
public class AdminService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private RentalOwnerRepository rentalOwnerRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<RentalOwner> getAllRentalOwners() {
        return rentalOwnerRepository.findAll();
    }

    public List<Renter> getAllRenters() {
        return renterRepository.findAll();
    }

    public User createUser(String username, String password, Role role) {
        User user = new User(username, password, role);
        return userRepository.save(user);
    }

    public ResponseEntity<String> updateUserRole(String username, Role newRole) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(newRole);
            userRepository.save(user);
            return ResponseEntity.ok("User role updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<String> updateUserStatus(String username, boolean active) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(active);
            userRepository.save(user);
            return ResponseEntity.ok("User status updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<String> updateRentalOwnerStatus(String username, boolean active) {
        Optional<RentalOwner> ownerOptional = rentalOwnerRepository.findByUsername(username);
        if (ownerOptional.isPresent()) {
            RentalOwner owner = ownerOptional.get();
            owner.setActive(active);
            rentalOwnerRepository.save(owner);
            return ResponseEntity.ok("Rental owner status updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental owner not found");
        }
    }

    public ResponseEntity<String> updateRenterStatus(String username, boolean active) {
        Optional<Renter> renterOptional = renterRepository.findByUsername(username);
        if (renterOptional.isPresent()) {
            Renter renter = renterOptional.get();
            renter.setActive(active);
            renterRepository.save(renter);
            return ResponseEntity.ok("Renter status updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Renter not found");
        }
    }

    public Renter createRenter(String username, String password) {
        Renter renter = new Renter(username, password);
        return renterRepository.save(renter);
    }

    public RentalOwner createRentalOwner(String username, String password) {
        RentalOwner rentalOwner = new RentalOwner(username, password);
        return rentalOwnerRepository.save(rentalOwner);
    }

    public User updateUser(String username, String newUsername, String newPassword, Role newRole) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(newUsername);
            user.setPassword(newPassword);
            user.setRole(newRole);
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public ResponseEntity<String> deleteUser(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            userRepository.deleteByUsername(username);
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<String> deleteRenter(String username) {
        Optional<Renter> renterOptional = renterRepository.findByUsername(username);
        if (renterOptional.isPresent()) {
            renterRepository.deleteByUsername(username);
            return ResponseEntity.ok("Renter deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Renter not found");
        }
    }

    public ResponseEntity<String> deleteRentalOwner(String username) {
        Optional<RentalOwner> ownerOptional = rentalOwnerRepository.findByUsername(username);
        if (ownerOptional.isPresent()) {
            rentalOwnerRepository.deleteByUsername(username);
            return ResponseEntity.ok("Rental owner deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental owner not found");
        }
    }
}



