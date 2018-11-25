package pl.speedlog.spock.example.repository;

import org.springframework.data.repository.CrudRepository;
import pl.speedlog.spock.example.domain.model.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByVin(String vin);

}
