package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład możliwości w sekcji "then".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WhenThenExampleSpec extends Specification {

    def "Should sort list"() {
        when:
            def result = [2, 1, 4, 3].sort()
        then:
            def sortedList = [1, 2, 3, 4] // zdefiniowanie spodziewanego wyniku
            result == sortedList    // asercja
            result.size() == 4      // kolejna asercja
    }

}
