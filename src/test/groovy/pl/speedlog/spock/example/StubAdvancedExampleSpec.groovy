package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład zaawansowanego stuba.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StubAdvancedExampleSpec extends Specification {

    CarDAO carDAO = Stub(CarDAO)
    def carService = new CarService(carDAO)

    def setup() {
        carDAO.findById(_) >> { Long id ->
            if (id <= 0L) {
                throw new IllegalArgumentException()
            }
            return new Car(id: 1, brand: "Volkswagen")
        }
    }

    def "Should return car brand by id"() {
        when:
            def result = carService.getCarBrand(1)
        then:
            result == "Volkswagen"
    }

    def "Should throw IllegalArgumentException when id is lower then 1"() {
        when:
            carService.getCarBrand(0)
        then:
            thrown(IllegalArgumentException)
    }

    def "Should return 3 brand names"() {
        given:
            carDAO.findRandom() >>> [new Car(id: 1, brand: "Volkswagen"),
                                 new Car(id: 2, brand: "BMW"),
                                 new Car(id: 3, brand: "Honda")]
        when:
            def result = carService.getThreeRandomCars()
        then:
            result.size() == 3
            result.brand == ["Volkswagen", "BMW", "Honda"]
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

        List<Car> getThreeRandomCars() {
            List<Car> cars = new ArrayList<>()
            cars << carDAO.findRandom()
            cars << carDAO.findRandom()
            cars << carDAO.findRandom()
            return cars
        }
    }

    interface CarDAO {

        /**
         * Rzuca wyjątek, gdy id jest null.
         */
        Car findById(Long id)

        Car findRandom()
    }

    class Car {
        Long id
        String brand
    }

}