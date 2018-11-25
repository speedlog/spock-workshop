package pl.speedlog.spock.example.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.speedlog.spock.example.controller.dto.CarInfo;
import pl.speedlog.spock.example.domain.model.Car;
import pl.speedlog.spock.example.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarInfo getCarByVin(String vin) {
        Car car = carRepository.findByVin(vin);
        return carMapper.carToCarInfo(car);
    }

    public void addCar(CarInfo carInfo) {
        Car car = carMapper.carInfoToCar(carInfo);
        carRepository.save(car);
    }
}
