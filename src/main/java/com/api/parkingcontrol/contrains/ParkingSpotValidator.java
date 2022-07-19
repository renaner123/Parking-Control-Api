package com.api.parkingcontrol.contrains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.services.CarService;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Valida se os parâmetros parkingSpotNumber, licensePlateCar e
 * o conjunto apartment + Block do ParkingSpotDto já possuem cadastro no banco
 */
public class ParkingSpotValidator implements ConstraintValidator<ParkingSpot, ParkingSpotDto> {
    final ParkingSpotService parkingSpotService;
    final CarService carService;
    private static final Logger log = LoggerFactory.getLogger(ParkingSpotValidator.class);

    public ParkingSpotValidator(ParkingSpotService parkingSpotService, CarService carService) {
        log.info("Entrou na classe ParkingSpotValidator ");
        this.parkingSpotService = parkingSpotService;
        this.carService = carService;
    }

    @Override
    public boolean isValid(ParkingSpotDto value, ConstraintValidatorContext context) {
        log.info("Iniciou ParkingSpotValidator");

        if (!carService.existsByLicensePlateCar(value.getLicensePlateCar()) &&
            !parkingSpotService.existsByParkingSpotNumber(value.getParkingSpotNumber()) &&
                !parkingSpotService.existsByApartmentAndBlock(value.getApartment(), value.getBlock())) {
            return true;
        }
        return false;

    }
}