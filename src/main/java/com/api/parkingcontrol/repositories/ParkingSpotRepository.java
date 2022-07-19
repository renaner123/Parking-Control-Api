package com.api.parkingcontrol.repositories;

import java.util.UUID;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Não precisaria, pois já está contido no extends
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
}
