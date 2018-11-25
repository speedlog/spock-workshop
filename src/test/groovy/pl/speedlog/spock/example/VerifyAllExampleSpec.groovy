package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia sekcji "verifyAll".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class VerifyAllExampleSpec extends Specification {

    def "Should check Person information"() {
        given:
            def person = simulateGettingPersonFromDatabase()
        expect:
            verifyAll(person) {
                name == "John"
                surname == "Rammbo"
                phone == "555-556-555"
                email == "john@rambo.com"

            }
    }

    Person simulateGettingPersonFromDatabase() {
        return new Person(
                name: "John",
                surname: "Rambo",
                phone: "555-555-555",
                email: "john@rambo.com"
        )
    }

    class Person {
        String name
        String surname
        String phone
        String email
    }
}