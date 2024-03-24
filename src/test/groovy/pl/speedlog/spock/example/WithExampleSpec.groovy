package pl.speedlog.spock.example

import org.spockframework.runtime.ConditionNotSatisfiedError
import spock.lang.FailsWith
import spock.lang.Specification

import java.time.LocalDate

/**
 * Przykład użycia sekcji "with".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WithExampleSpec extends Specification {

    @FailsWith(ConditionNotSatisfiedError)
    def "Should check Person information"() {
        given:
            def person = simulateGettingPersonFromDatabase()
        expect:
            with(person) {
                name == "John"
                surname == "Rambo"
            }
            checkAddress(person.address)
            checkDocument(person.document)
    }

    void checkAddress(Address address) {
        with(address) {
            street == "Green Tree 123"
            localNumber == "33B-BLABLA" // <-------------------------- WRONG VALUE
            city == "Los Angeles"
        }
    }

    void checkDocument(Document document) {
        with(document) {
            series == "AWB123"
            expirationDate.year == 1410 // <-------------------------- WRONG VALUE
            expirationDate.month == LocalDate.now().month
            expirationDate.dayOfMonth == LocalDate.now().dayOfMonth
        }
    }

    Person simulateGettingPersonFromDatabase() {
        return new Person(
                name: "John",
                surname: "Rambo",
                address: new Address(
                        street: "Green Tree 123",
                        localNumber: "33B",
                        city: "Los Angeles"
                ),
                document: new Document(
                        series: "AWB123",
                        expirationDate: LocalDate.now()
                )
        )
    }

    class Person {
        String name
        String surname
        Address address
        Document document
    }

    class Address {
        String street
        String localNumber
        String city
    }

    class Document {
        String series
        LocalDate expirationDate
    }
}