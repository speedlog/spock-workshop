package pl.speedlog.spock.workshop

import spock.lang.Specification

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class CarBrandCheckerUnrollSpec extends Specification {

    def "For brand #brand and production year #productionYear car is #expectedResult"() {
        given:
            def carBrandChecker = new CarBrandChecker()
        when:
            def result = carBrandChecker.check(brand, productionYear)
        then:
            result == expectedResult
        where:
            brand   | productionYear || expectedResult
            "HONDA" | 1975           || "HONDA oldtimer"
            "HONDA" | 1976           || "HONDA"
            "VW"    | 1970           || "VW oldtimer"
            "VW"    | 1971           || "VW"
            "BMW"   | 1970           || "BMW oldtimer"
            "BMW"   | 1971           || "BMW"
            "SKODA" | 1970           || "Unknown brand"
            "SKODA" | 2000           || "Unknown brand"
    }
}
