package pl.speedlog.spock.example.domain.service;

import org.springframework.stereotype.Component;
import pl.speedlog.spock.example.controller.dto.CarInfo;
import pl.speedlog.spock.example.domain.model.Car;

@Component
public class CarMapper {

    public CarInfo carToCarInfo(Car car) {
        return new CarInfo(
                car.getVin(),
                car.getBrand(),
                car.getModel()
        );
    }

    public Car carInfoToCar(CarInfo carInfo) {
        Car car = new Car();
        car.setVin(carInfo.getVin());
        car.setBrand(carInfo.getBrand());
        car.setModel(carInfo.getModel());
        return car;

    }
}
