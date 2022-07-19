package com.api.parkingcontrol.repositories;

import javax.persistence.criteria.Order;

import com.api.parkingcontrol.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Não precisaria, pois já está contido no extends
public interface CarRepository extends JpaRepository<CarModel, Integer> {
    
    boolean existsByLicensePlateCar(String licensePlateCar);
}
