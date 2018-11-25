package pl.speedlog.spock.example

import org.spockframework.mock.ISpockMockObject
import spock.lang.Specification

/**
 * Przykład działa stuba - zwracanie domyślnych wartości.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StubDefaultReturnExampleSpec extends Specification {

    def "Test default values return from stub"() {
        given:
            def stub = Stub(SomeClass)
        expect:
            stub.primitiveType() == 0
            stub.numberType() == 0 as BigInteger
            stub.someString() == ""
            stub.someList() == []
            stub.findCar() instanceof ISpockMockObject //dummy object
            stub.findCar() instanceof Car              //dummy object
    }

    interface SomeClass {
        int primitiveType()
        BigInteger numberType()
        String someString()
        List someList()
        Car findCar()
    }

    interface Car {
        String findByName()
    }

}