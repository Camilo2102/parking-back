package co.com.parking.service;

import co.com.parking.models.Car;
import co.com.parking.repository.CarRepository;
import co.com.parking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService extends GeneralService<Car>{

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public CarService(CarRepository carRepository,ClientRepository clientRepository) {
        super(carRepository);
        this.carRepository = carRepository;
        this.clientRepository=clientRepository;
    }

    public Car addCar(Car car){
        carRepository.save(car);
        return car;
    }

    public Car searchCar(String id){
        return carRepository.findByIdCar(id);
    }

    public List<Car> getAll(){
        return (List<Car>) carRepository.findAll();
    }

    public Car findByIdClient(String idClient){
        return carRepository.findByClient(clientRepository.findById(idClient));
    }

}
