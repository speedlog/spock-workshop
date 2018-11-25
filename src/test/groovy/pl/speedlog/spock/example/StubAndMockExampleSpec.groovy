package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład jednoczesnej deklaracji stuba i mocka.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StubAndMockExampleSpec extends Specification {

    def carDAO = Mock(CarDAO)
    def carService = new CarService(carDAO)

    def "Should persist and return car"() {
        given:
            Car car = new Car(id: 1, brand: "BMW")
            carDAO.persist(car) >> car
        when:
            def result = carService.save(car)
        then:
            1 * carDAO.persist(car)
            result == car
    }

    def "Should persist and return car - it works"() {
        given:
            Car car = new Car(id: 1, brand: "BMW")
        when:
            def result = carService.save(car)
        then:
            1 * carDAO.persist(car) >> car
            result == car
    }

    class CarService {
        final CarDAO carDAO

        CarService(CarDAO carDAO) {
            this.carDAO = carDAO
        }

        Car save(Car car) {
            return carDAO.persist(car)
        }
    }

    interface CarDAO {
        Car persist(Car car)
    }

    class Car {
        Long id
        String brand
    }

}