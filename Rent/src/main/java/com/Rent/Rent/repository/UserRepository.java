package com.Rent.Rent.repository;


import com.Rent.Rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods if needed
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}

