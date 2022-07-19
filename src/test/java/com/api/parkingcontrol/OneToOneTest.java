/* package com.api.parkingcontrol;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.api.parkingcontrol.models.CarModel;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.CarRepository;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

@SpringBootTest
public class OneToOneTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;
    @Test
    void saveOrderOperation(){
        // create Order object
        CarModel carModel = new CarModel();
        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();

        parkingSpotModel.setParkingSpotNumber("300B");
        parkingSpotModel.setResponsibleName("responsibleName");
        parkingSpotModel.setApartment("apartment");
        parkingSpotModel.setBlock("block");
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        carRepository.save(carModel);
        parkingSpotRepository.save(parkingSpotModel);
    }

} */