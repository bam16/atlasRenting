package com.Rent.Rent.model;

import com.Rent.Rent.Role;
import jakarta.persistence.*;

@Entity
@Table(name="rentalOwners")
public class RentalOwner extends User {

    public RentalOwner() {
    }
    public RentalOwner(String username, String password) {
        super(username, password, Role.RENTAL_OWNER);

    }
}