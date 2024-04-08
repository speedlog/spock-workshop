package pl.speedlog.spock.example


import spock.lang.Shared
import spock.lang.Specification
/**
 * Przykład konfiguracji dla wszystkich metod testowych.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class BeforeAndCleanUpExampleSpec extends Specification {

    @Shared
    String csvText

    def someVariable = ""

    def setupSpec() {
        csvText = this.getClass().getResource( '/ULIC_Urzedowy_2018-11-10.csv' ).text
        //someVariable = "setup this once before running specification"
    }

    def cleanupSpec() {
        csvText = null
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