package com.Rent.Rent.service;

import com.Rent.Rent.model.RentalOwner;
import com.Rent.Rent.repository.RentalOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalOwnerService {

    private RentalOwnerRepository rentalOwnerRepository;

    @Autowired
    public RentalOwnerService(RentalOwnerRepository rentalOwnerRepository) {
        this.rentalOwnerRepository = rentalOwnerRepository;
    }

    public List<RentalOwner> getAllRentalOwners() {
        return this.rentalOwnerRepository.findAll();
    }

    public RentalOwner createRentalOwner(RentalOwner rentalOwner) {
        return this.rentalOwnerRepository.save(rentalOwner);
    }
}
