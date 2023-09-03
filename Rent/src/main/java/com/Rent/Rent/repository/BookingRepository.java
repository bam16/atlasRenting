package com.Rent.Rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Rent.Rent.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Additional methods for specific querying or operations if needed
}
