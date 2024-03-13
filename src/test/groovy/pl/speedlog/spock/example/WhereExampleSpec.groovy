package pl.speedlog.spock.example

import com.xlson.groovycsv.CsvParser
import spock.lang.Shared
import spock.lang.Specification

/**
 * Przykład dla użycia bloku "where".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WhereExampleSpec extends Specification {

    def "Should validate by age and sex using data table"() {
        given:
            def validator = new AgeAndSexValidator()
        when:
            def result = validator.validate(age, sex)
        then:
            result == expectedResult
        where:
            // Data table
            // do oddzielenia zmiennych stosuje się pojedynczy znak |
            // do oddzielenia danych wejściowych i wyjściowych można zastosować ciąg znaków || w celu lepszej wizualizacji
            age | sex || expectedResult
            15  | "K" || false
            16  | "K" || true
            99  | "K" || true
            17  | "M" || false
            18  | "M" || true
            99  | "M" || true
    }

    def "Should validate by age and sex using data pipeline"() {
        given:
            def validator = new AgeAndSexValidator()
        when:
            def result = validator.validate(age, sex)
        then:
            result == expectedResult
        where:
            age << [15, 16, 99, 17, 18, 99]
            sex << ["K", "K", "K", "M", "M", "M"]
            expectedResult << [false, true, true, false, true, true]
    }

    @Shared csvData = new CsvParser().parse([skipLines: 1] as Map, """
            name,sex,age
            Anna,K,16
            John,M,18
            Mark,M,99
            Alex,K,99""")

    def "Should validate by age and sex using Multi-Variable Data Pipes"() {
        given:
            def validator = new AgeAndSexValidator()
        when:
            def result = validator.validate(age as int, sex as String)
        then:
            result == true
        where:
            [_, sex, age] << csvData
    }

    class AgeAndSexValidator {

        /**
         * Walidacja wg. płci i wieku.
         *
         * @param wiek podany jako liczba całkowita
         * @param płeć - "M" dla mężczyzny, "K" dla kobiety
         * @return boolean
         */
        boolean validate(int age, String sex) {
            if (age >= 16 && sex == "K") {
                return true
            }
            if (age >= 18 && sex == "M") {
                return true
            }
            return false
        }

    }

}