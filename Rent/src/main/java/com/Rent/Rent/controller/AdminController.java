package com.Rent.Rent.controller;

import com.Rent.Rent.Role;
import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.model.Renter;
import com.Rent.Rent.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Rent.Rent.service.AdminService;
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

    @PutMapping("/users/{username}/role")
    public ResponseEntity<String> updateUserRole(
            @PathVariable String username,
            @RequestParam Role newRole
    ) {
        adminService.updateUserRole(username, newRole);
        return ResponseEntity.ok("User role updated");
    }

    @PutMapping("/users/{username}/status")
    public ResponseEntity<String> updateUserStatus(
            @PathVariable String username,
            @RequestParam boolean active
    ) {
        adminService.updateUserStatus(username, active);
        return ResponseEntity.ok("User status updated");
    }

    @PutMapping("/rental-owners/{ownerUsername}/status")
    public ResponseEntity<String> updateRentalOwnerStatus(
            @PathVariable String username,
            @RequestParam boolean active
    ) {
        adminService.updateRentalOwnerStatus(username, active);
        return ResponseEntity.ok("Rental owner status updated");
    }

    @PutMapping("/renters/{renterUsername}/status")
    public ResponseEntity<String> updateRenterStatus(
            @PathVariable String username,
            @RequestParam boolean active
    ) {
        adminService.updateRenterStatus(username, active);
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
        @PutMapping("/users/{username}")
        public ResponseEntity<User> updateUser(
                @PathVariable String username,
                @RequestParam String newUsername,
                @RequestParam String newPassword,
                @RequestParam Role newRole
        ) {
            User updatedUser = adminService.updateUser(username, newUsername, newPassword, newRole);
            return ResponseEntity.ok(updatedUser);
        }
    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        ResponseEntity<String> response = adminService.deleteUser(username);
        return response;
    }

    @DeleteMapping("/renters/{renterUsername}")
    public ResponseEntity<String> deleteRenter(@PathVariable String username) {
        ResponseEntity<String> response = adminService.deleteRenter(username);
        return response;
    }

    @DeleteMapping("/rental-owners/{ownerUsername}")
    public ResponseEntity<String> deleteRentalOwner(@PathVariable String username) {
        ResponseEntity<String> response = adminService.deleteRentalOwner(username);
        return response;
    }

}



