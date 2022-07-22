package com.api.parkingcontrol.repositories;

import java.util.List;
import java.util.UUID;

import com.api.parkingcontrol.models.ParkingSpotModel;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository //Não precisaria, pois já está contido no extends
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByBlock(String block);
    boolean existsByApartmentAndBlock(String apartment, String block);
    List<ParkingSpotModel> findByBlock(String block);
}
