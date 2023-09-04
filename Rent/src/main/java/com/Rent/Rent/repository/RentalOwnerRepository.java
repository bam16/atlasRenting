package com.Rent.Rent.repository;

import com.Rent.Rent.model.RentalOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalOwnerRepository extends JpaRepository<RentalOwner, Long> {
    Optional<RentalOwner> findByUsername(String username);
    void deleteByUsername(String username);
}
