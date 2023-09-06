package com.Rent.Rent.controller;

import com.Rent.Rent.Role;
import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.model.Renter;
import com.Rent.Rent.model.User;
import com.Rent.Rent.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/rental-owners")
    public ResponseEntity<List<RentalOwner>> getAllRentalOwners() {
        List<RentalOwner> rentalOwners = adminService.getAllRentalOwners();
        return ResponseEntity.ok(rentalOwners);
    }

    @GetMapping("/renters")
    public ResponseEntity<List<Renter>> getAllRenters() {
        List<Renter> renters = adminService.getAllRenters();
        return ResponseEntity.ok(renters);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Role role
    ) {
        User user = adminService.createUser(username, password, role);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<String> updateUserRole(
            @PathVariable Long userId,
            @RequestParam Role newRole
    ) {
        adminService.updateUserRole(userId, newRole);
        return ResponseEntity.ok("User role updated");
    }

    @PutMapping("/users/{userId}/status")
    public ResponseEntity<String> updateUserStatus(
            @PathVariable Long userId,
            @RequestParam boolean active
    ) {
        adminService.updateUserStatus(userId, active);
        return ResponseEntity.ok("User status updated");
    }

    @PutMapping("/rental-owners/{ownerId}/status")
    public ResponseEntity<String> updateRentalOwnerStatus(
            @PathVariable Long ownerId,
            @RequestParam boolean active
    ) {
        adminService.updateRentalOwnerStatus(ownerId, active);
        return ResponseEntity.ok("Rental owner status updated");
    }

    @PutMapping("/renters/{renterId}/status")
    public ResponseEntity<String> updateRenterStatus(
            @PathVariable Long renterId,
            @RequestParam boolean active
    ) {
        adminService.updateRenterStatus(renterId, active);
        return ResponseEntity.ok("Renter status updated");
    }

    @PostMapping("/renters")
    public ResponseEntity<Renter> createRenter(
            @RequestParam String username,
            @RequestParam String password
    ) {
        Renter renter = adminService.createRenter(username, password);
        return ResponseEntity.ok(renter);
    }

    @PostMapping("/rental-owners")
    public ResponseEntity<RentalOwner> createRentalOwner(
            @RequestParam String username,
            @RequestParam String password
    ) {
        RentalOwner rentalOwner = adminService.createRentalOwner(username, password);
        return ResponseEntity.ok(rentalOwner);
    }
        @PutMapping("/users/{userId}")
        public ResponseEntity<User> updateUser(
                @PathVariable Long userId,
                @RequestParam String newUsername,
                @RequestParam String newPassword,
                @RequestParam Role newRole
        ) {
            User updatedUser = adminService.updateUser(userId, newUsername, newPassword, newRole);
            return ResponseEntity.ok(updatedUser);
        }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        ResponseEntity<String> response = adminService.deleteUser(userId);
        return response;
    }

    @DeleteMapping("/renters/{renterId}")
    public ResponseEntity<String> deleteRenter(@PathVariable Long renterId) {
        ResponseEntity<String> response = adminService.deleteRenter(renterId);
        return response;
    }

    @DeleteMapping("/rental-owners/{ownerId}")
    public ResponseEntity<String> deleteRentalOwner(@PathVariable Long ownerId) {
        ResponseEntity<String> response = adminService.deleteRentalOwner(ownerId);
        return response;
    }

}



