package com.api.parkingcontrol.contrains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Valida se os parâmetros parkingSpotNumber, licensePlateCar e
 * o conjunto apartment + Block do ParkingSpotDto já possuem cadastro no banco
 */
public class ParkingSpotValidator implements ConstraintValidator<ParkingSpot, ParkingSpotDto> {
    final ParkingSpotService parkingSpotService;
    private static final Logger log = LoggerFactory.getLogger(ParkingSpotValidator.class);

    public ParkingSpotValidator(ParkingSpotService parkingSpotService) {
        log.info("Entrou na classe ParkingSpotValidator ");
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public boolean isValid(ParkingSpotDto value, ConstraintValidatorContext context) {
        log.info("Iniciou ParkingSpotValidator");

        if (!parkingSpotService.existsByLicensePlateCar(value.getLicensePlateCar()) &&
                !parkingSpotService.existsByParkingSpotNumber(value.getParkingSpotNumber()) &&
                !parkingSpotService.existsByApartmentAndBlock(value.getApartment(), value.getBlock())) {
            return true;
        }
        return false;

    }
}