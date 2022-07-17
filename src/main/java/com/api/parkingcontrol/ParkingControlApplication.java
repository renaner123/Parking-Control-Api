package com.api.parkingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
@RestController
public class ParkingControlApplication {

	public static void main(String[] args) {
		log.info("Projeto iniciado");
		SpringApplication.run(ParkingControlApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Ola Mundo!";
	}


}
