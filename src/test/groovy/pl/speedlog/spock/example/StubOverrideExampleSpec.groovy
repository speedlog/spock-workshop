package pl.speedlog.spock.example

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

/**
 * Przykład nadpisywania stuba.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StubOverrideExampleSpec extends Specification {

    CarDAO carDAO = Stub {
        findById(1) >> new Car(id: 1, brand: "Honda")
    }
    def carService = new CarService(carDAO)

    def "Should return Honda from stub declaration"() {
        when:
            def result = carService.getCarBrand(1)
        then:
            result == "Honda"
    }

    @FailsWith(ConditionNotSatisfiedError)
    def "Should return BMW from override - it doesn't works"() {
        given:
            carDAO.findById(1) >> new Car(id: 1, brand: "BMW")
        when:
            def result = carService.getCarBrand(1)
        then:
            result == "BMW"
    }

    def "Should return BMW from override - it works"() {
        when:
            def result = carService.getCarBrand(1)
        then:
            carDAO.findById(1) >> new Car(id: 1, brand: "BMW")
            result == "BMW"
    }

    class CarService {
        final CarDAO carDAO

        CarService(CarDAO carDAO) {
            this.carDAO = carDAO
        }

        String getCarBrand(Long carId) {
            def car = carDAO.findById(carId)
            return car.brand
        }
    }

    interface CarDAO {

        /**
         * Rzuca wyjątek, gdy id jest null.
         */
        Car findById(Long id)
    }

    class Car {
        Long id
        String brand
    }

}