package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia metod pomocniczych.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class HelperMethodExampleSpec extends Specification {


    def "Should check Person information"() {
        given:
            def person = simulateGettingPersonFromDatabase()
        expect:
            person.name == "John"
            person.surname == "Rambo"
            checkAddress(person.address)
            checkDocument(person.document)
    }

    void checkAddress(Address address) {
        assert address.street == "Green Tree 123"
        assert address.localNumber == "33B"
        assert address.city == "Los Angeles"
    }

    void checkDocument(Document document) {
        assert document.series == "AWB123"
        assert document.expirationDate.year == new Date().year
        assert document.expirationDate.month == new Date().month
        assert document.expirationDate.day == new Date().day
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