package com.Rent.Rent.model;

import com.Rent.Rent.Role;
import jakarta.persistence.*;

@Entity
@Table
public class Admin  extends User{

    public Admin() {
    }
    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);

    }
}
