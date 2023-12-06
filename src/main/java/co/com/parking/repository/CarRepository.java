package co.com.parking.repository;

import co.com.parking.models.Car;
import co.com.parking.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CarRepository extends GeneralRepository<Car>{
    @Query("SELECT  c FROM Car c WHERE c.client =:client ")
    public Car findByClient(@Param("client") Optional<Client> client);
}
