package pl.speedlog.spock.example

import spock.lang.Specification

import javax.inject.Inject

/**
 * Przykład wstrzykiwania mocka poprzez "named parameters".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInjectByNamedParametersExampleSpec extends Specification {

    def "Should save new car to datasource"() {
        given:
            def carDAO = Mock(CarDAO)
            def service = new CarService(carDAO: carDAO)
        when:
            service.saveNewCar("SEAT", "WB12345")
        then:
            1 * carDAO.addNewCar("SEAT", "WB12345")
    }

    interface CarDAO {
        void addNewCar(String brand, String registrationNumber);
    }

    class CarService {

        @Inject
        CarDAO carDAO

        void saveNewCar(String brand, String registrationNumber) {
            carDAO.addNewCar(brand, registrationNumber)
        }
    }

}