package com.Rent.Rent.service;

import com.Rent.Rent.Role;
import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.model.Renter;
import com.Rent.Rent.model.User;
import com.Rent.Rent.repository.RentalOwnerRepository;
import com.Rent.Rent.repository.RenterRepository;
import com.Rent.Rent.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalOwnerRepository rentalOwnerRepository;

    @Autowired
    private RenterRepository renterRepository;

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

    public void updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setRole(newRole);
        userRepository.save(user);
    }

    public void updateUserStatus(Long userId, boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setActive(active);
        userRepository.save(user);
    }

    public void updateRentalOwnerStatus(Long ownerId, boolean active) {
        RentalOwner owner = rentalOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Rental owner not found"));
        owner.setActive(active);
        rentalOwnerRepository.save(owner);
    }

    public void updateRenterStatus(Long renterId, boolean active) {
        Renter renter = renterRepository.findById(renterId)
                .orElseThrow(() -> new EntityNotFoundException("Renter not found"));
        renter.setActive(active);
        renterRepository.save(renter);
    }

    public Renter createRenter(String username, String password) {
        Renter renter = new Renter(username, password);
        return renterRepository.save(renter);
    }

    public RentalOwner createRentalOwner(String username, String password) {
        RentalOwner rentalOwner = new RentalOwner(username, password);
        return rentalOwnerRepository.save(rentalOwner);
    }
    public User updateUser(Long userId, String newUsername, String newPassword, Role newRole) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            // Update user properties
            user.setUsername(newUsername);
            user.setPassword(newPassword);
            user.setRole(newRole);

            // Save the updated user
            return userRepository.save(user);
        }


        public ResponseEntity<String> deleteUser(Long userId) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                return ResponseEntity.ok("User deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        }

        public ResponseEntity<String> deleteRenter(Long renterId) {
            Optional<Renter> renterOptional = renterRepository.findById(renterId);
            if (renterOptional.isPresent()) {
                renterRepository.deleteById(renterId);
                return ResponseEntity.ok("Renter deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Renter not found");
            }
        }

        public ResponseEntity<String> deleteRentalOwner(Long ownerId) {
            Optional<RentalOwner> ownerOptional = rentalOwnerRepository.findById(ownerId);
            if (ownerOptional.isPresent()) {
                rentalOwnerRepository.deleteById(ownerId);
                return ResponseEntity.ok("Rental owner deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental owner not found");
            }
        }

}
