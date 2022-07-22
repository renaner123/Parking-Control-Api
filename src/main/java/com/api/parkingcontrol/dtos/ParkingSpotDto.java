package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.parkingcontrol.contrains.ParkingSpot;
import com.api.parkingcontrol.models.ParkingSpotModel;

import lombok.Data;

/**
 * Usado para transferir as informações recebidas no RequestBody para as entidades desejadas
 */
@Data
@ParkingSpot
public class ParkingSpotDto extends ParkingSpotModel{

    @NotBlank
    private String parkingSpotNumber;

    @NotBlank
    @Size(max = 7)
    private String licensePlateCar;

    @NotBlank
    private String brandCar;

    @NotBlank
    private String modelCar;

    @NotBlank
    private String colorCar;

    @NotBlank
    private String responsibleName;

    @NotBlank
    private String apartment;

    @NotBlank  
    private String block;

}
