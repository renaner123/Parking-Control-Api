package com.api.parkingcontrol.contrains;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.parkingcontrol.dtos.CarDto;
import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.services.CarService;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Valida se os parâmetros parkingSpotNumber, licensePlateCar e
 * o conjunto apartment + Block do ParkingSpotDto já possuem cadastro no banco
 */
public class CarValidator implements ConstraintValidator<Car, CarDto> {
    final CarService carService;
    private static final Logger log = LoggerFactory.getLogger(CarValidator.class);

    public CarValidator(CarService carService) {
        log.info("Entrou na classe CarValidator ");
        this.carService = carService;
    }

    @Override
    public boolean isValid(CarDto value, ConstraintValidatorContext context) {
        if(!carService.existsByLicensePlateCar(value.getLicensePlateCar())){
            return true;
        }
        return false;
    }
}    