package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład sprawdzania wyjątków.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class ExceptionExampleSpec extends Specification {

    def "Should throw CustomException"() {
        given:
            def exceptionThrower = new ExceptionThrower()
        when:
            exceptionThrower.throwException()
        then:
            def exception = thrown(CustomException) // sprawdzenie czy wyjątek jest konkretnego typu
            exception.errorCode == 1234 // sprawdzenie parametru wyjątku
    }

    class ExceptionThrower {
        void throwException() {
            throw new CustomException(errorCode: 1234)
        }
    }

    class CustomException extends Exception {
        int errorCode
    }

}