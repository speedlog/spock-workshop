package pl.speedlog.spock.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.speedlog.spock.example.controller.dto.CarInfo;
import pl.speedlog.spock.example.domain.service.CarService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @RequestMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public CarInfo getCarByVin(@RequestParam String vin) {
        return carService.getCarByVin(vin);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @RequestMapping("/car/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody @Valid CarInfo carInfo) {
        carService.addCar(carInfo);
    }
}
