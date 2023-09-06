package com.Rent.Rent.model;

import com.Rent.Rent.Role;
import jakarta.persistence.*;

@Entity
@Table(name="renters")
public class Renter extends User{

    public Renter() {
    }
    public Renter(String username, String password) {
        super(username, password, Role.RENTER);

    }


}
