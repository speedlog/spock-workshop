package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia adnotacji @Unroll.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class UnrollExampleSpec extends Specification {

    def "PESEL #pesel should be #validInfo"() {
        when:
            def result = PeselValidator.isValidPESEL(pesel)
        then:
            def expect = validInfo == "valid"
            result == expect
        where:
            pesel          || validInfo
            "81020683635"  || "valid"
            "81120683635"  || "unvalid"
            "69062518458"  || "valid"
            "69262518458"  || "unvalid"
            "83050893851"  || "valid"
            "83050893852"  || "unvalid"
    }

}

class PeselValidator {
    static boolean isValidPESEL(String pesel) {

        int psize = pesel.length()
        if (psize != 11) {
            return false
        }
        int[] weights = [ 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 ]
        int j = 0, sum = 0, control = 0
        int csum = Integer.valueOf(pesel.substring(psize - 1))
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i)
            j = Integer.valueOf(String.valueOf(c))
            sum += j * weights[i]
        }
        control = 10 - (sum % 10)
        if (control == 10) {
            control = 0
        }
        return (control == csum)

    }
}