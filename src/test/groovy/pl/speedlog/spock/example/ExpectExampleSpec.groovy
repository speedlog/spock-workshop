package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia bloku "expect".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class ExpectExampleSpec extends Specification {

    def "Should return greatest element from list"() {
        expect:
            Collections.max([4, 3, 1]) == 4
    }

}