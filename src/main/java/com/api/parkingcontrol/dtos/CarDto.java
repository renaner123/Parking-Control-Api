package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.parkingcontrol.contrains.Car;
import com.api.parkingcontrol.models.CarModel;

import lombok.Data;

@Data
@Car
public class CarDto extends CarModel {


}
