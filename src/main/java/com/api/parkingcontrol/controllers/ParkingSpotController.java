package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.api.parkingcontrol.contrains.ParkingSpot;
import com.api.parkingcontrol.contrains.ParkingSpotValidator;
import com.api.parkingcontrol.dtos.CarDto;
import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.CarModel;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.CarService;
import com.api.parkingcontrol.services.ParkingSpotService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//TODO jogar as inforações do log para um arquivo
@Slf4j
@Validated 
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    
    @Autowired
    private ParkingSpotService parkingSpotService;  
    @Autowired
    private CarService carService;
    
    public ParkingSpotController(ParkingSpotService parkingSpotService, CarService carService) {
        this.parkingSpotService = parkingSpotService;
        this.carService = carService;
    }

    /**
     * @Valid faz a validação conforme está nas anotações da classe ParkingSpotDto,
     *        caso algo esteja errado, retorna BadRequest
     * 
     * @param parkingSpotDto Json contendo as informações a serem armazenadas
     * @return Status created(201) com os dados que foram cadastrados
     */
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        var parkingSpotModel = new ParkingSpotModel();
        var carModel = new CarModel();
        // Converte Dto em Model
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        BeanUtils.copyProperties(parkingSpotDto, carModel);

        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        carService.save(carModel);
        parkingSpotModel.setCarModel(carModel);
        parkingSpotService.save(parkingSpotModel);
        log.info("Valor do ParkingSPotDto copiado {}:", parkingSpotModel.toString());
        log.info("Valor do CarDto copiado {}:", carModel.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotModel);
    }

    //TODO receber argumentos pra listar por tipo de carro, bloco ...
    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpot(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Listando todas as vagas de estacionamento usadas");
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdParkingSpot(@PathVariable(value = "id") UUID id) {

        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByIdParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        } else {
            log.info("Vaga {} deletada!", id);
            parkingSpotService.deleteByIdParkingSpot(parkingSpotModelOptional.get());// como é optcional precisa usar o
                                                                                     // get pra pegar a instancia
            return ResponseEntity.status(HttpStatus.OK).body("Parking Spot was deleted!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDto parkingSpotDto){
                                                        
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);

        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        var parkingSpotModel = new ParkingSpotModel();
        var carModel = new CarModel();
        // Converte Dto em Model
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        BeanUtils.copyProperties(parkingSpotDto, carModel);

        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        carService.save(carModel);
        parkingSpotModel.setCarModel(carModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
        // TODO estudar meios de conversão para alterar as informações no banco somente do que veio alterado
    }

}
