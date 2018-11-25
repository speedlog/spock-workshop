package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia sekcji "with".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WithExampleSpec extends Specification {

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
            localNumber == "33B"
            city == "Los Angeles"
        }
    }

    void checkDocument(Document document) {
        with(document) {
            series == "AWB123"
            expirationDate.year == new Date().year
            expirationDate.month == new Date().month
            expirationDate.day == new Date().day
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
                        expirationDate: new Date()
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
        Date expirationDate
    }
}