package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład podstawowego stuba.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StubSimpleExampleSpec extends Specification {

    def "Should return car brand by id"() {
        given:
            def carDAO = Stub(CarDAO)
            carDAO.findById(1) >> new Car(id: 1, brand: "Volkswagen")
        and:
            def carService = new CarService(carDAO)
        when:
            def result = carService.getCarBrand(1)
        then:
            result == "Volkswagen"
    }

    def "Should return car brand by id - another example"() {
        given:
            CarDAO carDAO = Stub {
                findById(1) >> new Car(id: 1, brand: "Volkswagen")
            }
        and:
            def carService = new CarService(carDAO)
        when:
            def result = carService.getCarBrand(1)
        then:
            result == "Volkswagen"
    }

    def "Should throw IllegalArgumentException when id is null"() {
        given:
            CarDAO carDAO = Stub {
                findById(null) >> { throw new IllegalArgumentException() }
            }
        and:
            def carService = new CarService(carDAO)
        when:
            carService.getCarBrand(null)
        then:
            thrown(IllegalArgumentException)
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