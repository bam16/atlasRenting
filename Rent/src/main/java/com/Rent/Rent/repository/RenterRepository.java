package com.Rent.Rent.repository;

import com.Rent.Rent.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RenterRepository extends JpaRepository<Renter,Long> {
    Optional<Renter> findByUsername(String username);
    void deleteByUsername(String username);
}
