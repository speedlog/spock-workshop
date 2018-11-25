package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład użycia bloku "interaction".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInteractionBlockExampleSpec extends Specification {

    def emailService = Mock(EmailService)
    def messageService = new MessageService(emailService: emailService)

    def "Should send message with long text - it will fail"() {
        // Przeniesiona została interakcja, ale bez deklaracji zmiennej "longText"
        // 1 * emailService.sendMail(longText)
        when:
            messageService.sendLongMail()
        then:
            def longText = "It's very long text, so it will be good to assign it to variable"
            1 * emailService.sendMail(longText)
    }

    def "Should send message with long text"() {
        // Przeniesiona została cała zawartość bloku "interaction"
        // def longText = "It's very long text, so it will be good to assign it to variable"
        // 1 * emailService.sendMail(longText)
        when:
            messageService.sendLongMail()
        then:
            interaction {
                def longText = "It's very long text, so it will be good to assign it to variable"
                1 * emailService.sendMail(longText)
            }
    }

    class MessageService {

        EmailService emailService

        void sendLongMail() {
            def longText = "It's very long text, so it will be good to assign it to variable"
            emailService.sendMail(longText)
        }

    }

    interface EmailService {
        void sendMail(String msg);
    }

}