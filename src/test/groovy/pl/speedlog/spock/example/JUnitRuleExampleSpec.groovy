package pl.speedlog.spock.example

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

/**
 * Przykład użycia Rule JUnit'a.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class JUnitRuleExampleSpec extends Specification {

    @Rule
    TemporaryFolder folder = new TemporaryFolder()

    def "Test temporary folder"() {
        given:
            def createdFile = folder.newFile("new.txt")
        when:
            def filename = createdFile.name
        then:
            filename == "new.txt"
    }
}