package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykłady prezentacji błędów podczas asercji w testach.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class ErrorHandlingExampleSpec extends Specification {

    def "Should compare numbers"() {
        given:
            int number1 = 1
            int number2 = 2
        when:
            int result = number1 + number2
        then:
            result == 4
    }

    def "Should compare text"() {
        when:
            String text = "Some extra TEXT!"
        then:
            text == "Some Extra TEXT"
    }

    def "Should compare modified text"() {
        when:
            String text = "Some extra TEXT!"
        then:
            text.toUpperCase().substring(0, 4) == "Some Extra TEXT"
    }

    def "Should compare lists"() {
        when:
            List<Integer> list = Arrays.asList(1, 2, 3)
        then:
            list == Arrays.asList(1, 5, 3, 10)
    }

    def "Should compare nested object"() {
        when:
            Container container1 = new Container("text1")
            Container container2 = new Container(container1)
            Container container3 = new Container(container2)
        then:
            container3.getContainer().getContainer().getContainer().text == "text1"
    }

    def "Should check interaction"() {
        given:
            def threadMock = Mock(Runnable)
        when:
            threadMock.run()
        then:
            2 * threadMock.run()
    }

    def "Should check exception"() {
        given:
            String something = null
        when:
            something.toUpperCase()
        then:
            thrown(IllegalArgumentException)
    }

    class Container {
        String text
        Container container

        Container(Container container) {
            this.container = container
        }

        Container(String text) {
            this.text = text
        }

        String getText() {
            return text
        }

        Container getContainer() {
            return container
        }
    }


}