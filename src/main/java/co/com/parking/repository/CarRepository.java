package co.com.parking.repository;

import co.com.parking.models.Car;
import co.com.parking.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarRepository extends GeneralRepository<Car>{
    public Car findByIdCar(String idCar);
    @Query("SELECT  c FROM Car c WHERE c.client =:client ")
    public Car findByClient(@Param("client") Optional<Client> client);
}
