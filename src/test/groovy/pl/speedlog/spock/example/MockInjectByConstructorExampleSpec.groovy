package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład wstrzykiwania mocka za pomocą konstruktora.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInjectByConstructorExampleSpec extends Specification {

    def "Should save new car to datasource"() {
        given:
            def carDAO = Mock(CarDAO)
            def service = new CarService(carDAO)
        when:
            service.saveNewCar("SEAT", "WB12345")
        then:
            1 * carDAO.addNewCar("SEAT", "WB12345")
    }

    interface CarDAO {
        void addNewCar(String brand, String registrationNumber);
    }

    class CarService {
        final CarDAO carDAO

        CarService(CarDAO carDAO) {
            this.carDAO = carDAO
        }

        void saveNewCar(String brand, String registrationNumber) {
            carDAO.addNewCar(brand, registrationNumber)
        }
    }

}