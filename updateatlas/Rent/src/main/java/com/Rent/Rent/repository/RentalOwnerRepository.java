package com.Rent.Rent.repository;

import com.Rent.Rent.model.RentalOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOwnerRepository extends JpaRepository<RentalOwner, Long> {

}
