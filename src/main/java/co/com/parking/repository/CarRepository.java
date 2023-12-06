package co.com.parking.repository;

import co.com.parking.models.Car;
import co.com.parking.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends GeneralRepository<Car>{
    @Override
    @Query("SELECT c FROM Car c")
    List<Car> findByFilter(Car car, Pageable page);

    @Override
    @Query("SELECT count(c) FROM Car c")
    long countByFilter(Car car);

    @Query("SELECT  c FROM Car c WHERE c.client =:client ")
    public Car findByClient(@Param("client") Optional<Client> client);
}
