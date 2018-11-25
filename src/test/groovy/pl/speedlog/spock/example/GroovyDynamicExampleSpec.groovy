package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład dynamicznego kodu Groovy.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class GroovyDynamicExampleSpec extends Specification {

    // przykład inicjalizacji obiektów za pomocą mapy
    def parameters = [name: "John", surname: "Rambo", age: 67]
    def personPesel = new PersonInPesel(parameters)
    def personCepik = new PersonInCepik(parameters)

    def "Should objects be the same"() {
        expect:
            personPesel.name == personCepik.name
            personPesel.surname == personCepik.surname
            personPesel.age == personCepik.age
    }

    def "Should clear name and surname"() {
        when:
            clearNameAndSurename(personPesel)
            clearNameAndSurename(personCepik)
        then:
            personPesel.name == null
            personPesel.surname == null
            personCepik.name == null
            personCepik.surname == null
    }

    void clearNameAndSurename(def person) {
        person.name = null
        person.surname = null
    }

    class PersonInPesel {
        String name
        String surname
        int age
    }

    class PersonInCepik {
        String name
        String surname
        int age
    }

}