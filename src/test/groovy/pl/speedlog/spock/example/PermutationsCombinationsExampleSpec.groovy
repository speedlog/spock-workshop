package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia funkcji Groovy `permutations()` i `combinations()`
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class PermutationsCombinationsExampleSpec extends Specification {

    def "Should run permutations"() {
        given:
            def permutations = ['a', 'b', 'c'].permutations()
        expect:
            println permutations
            permutations == [['a', 'b', 'c'], ['c', 'b', 'a'], ['b', 'a', 'c'], ['c', 'a', 'b'], ['a', 'c', 'b'], ['b', 'c', 'a']] as Set
    }

    def "Should run combinations"() {
        given:
            def combinations = [['a', 'b'], [1, 2]].combinations()
        expect:
            println combinations
            combinations == [['a', 1], ['b', 1], ['a', 2], ['b', 2]]
    }

    def "Should run combinations with transform"() {
        given:
            def combinations = [['a', 'b'], [1, 2]].combinations { element1, element2 -> element1 + '' + element2 }
        expect:
            println combinations
            combinations == ['a1', 'b1', 'a2', 'b2']
    }
}