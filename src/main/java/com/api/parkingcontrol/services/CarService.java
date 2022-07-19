package com.api.parkingcontrol.services;

import javax.transaction.Transactional;

import com.api.parkingcontrol.models.CarModel;
import com.api.parkingcontrol.repositories.CarRepository;
import org.springframework.stereotype.Service;

//Usado para regras de negócios. Intermediário entre Controllers e Repositories
@Service
public class CarService {

    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Garante que não irá ter dados quebrados, faz rollback
    @Transactional
    public CarModel save(CarModel carModel) {
        return carRepository.save(carModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return carRepository.existsByLicensePlateCar(licensePlateCar);
    }

}
