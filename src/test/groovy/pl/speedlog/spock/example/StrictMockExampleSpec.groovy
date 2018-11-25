package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład weryfikacji wywołań mocków w trybie "strict".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class StrictMockExampleSpec extends Specification {

    def "Should send email - fail, because of strict mode"() {
        given:
            def emailService = Mock(EmailService)
            def messageService = new MessageService(emailService: emailService)
        when:
            messageService.sendEmailAndCheckMail()
        then:
            // A
            1 * emailService.sendMail()
            // B - błąd, ponieważ nie spodziwamy się innych interakcji niż ta powyżej
            0 * _
    }

    class MessageService {

        EmailService emailService

        void sendEmailAndCheckMail() {
            emailService.sendMail() // A
            emailService.checkMail() // B
        }

    }

    interface EmailService {
        void sendMail()

        void checkMail()
    }

}