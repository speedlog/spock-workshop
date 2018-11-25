package pl.speedlog.spock.example

import org.junit.BeforeClass
import spock.lang.Shared
import spock.lang.Specification

/**
 * Przykład użycia adnotacji @BeforeClass.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class BeforeAndCleanUpExampleSpec extends Specification {

    @Shared
    String csvText

    @BeforeClass
    def beforeClass() {
        csvText = this.getClass().getResource( '/ULIC_Urzedowy_2018-11-10.csv' ).text
    }

    def "Should check line number"() {
        given:
            def lines = csvText.split("\n")
        expect:
            lines.size() == 270_135
    }

    def "Should csv size"() {
        expect:
            csvText.size() == 14_446_513
    }

}