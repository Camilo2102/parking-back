package co.com.parking.service;

import co.com.parking.models.Car;
import co.com.parking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService extends GeneralService<Car>{

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        super(carRepository);
        this.carRepository = carRepository;
    }
}
