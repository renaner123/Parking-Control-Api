package com.api.parkingcontrol.services;

import java.beans.JavaBean;

import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Usado para regras de negócios. Intermediário entre Controllers e Repositories
@Service
public class ParkingSpotService {
    
    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository){
        this.parkingSpotRepository = parkingSpotRepository;
    }
}
