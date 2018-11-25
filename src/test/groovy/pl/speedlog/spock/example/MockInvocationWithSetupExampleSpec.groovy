package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład konfiguracji mock'a w metodzie setup.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInvocationWithSetupExampleSpec extends Specification {

    def emailService = Mock(EmailService)
    def messageService = new MessageService(emailService: emailService)

    def setup() {
        1 * emailService.sendMail()
    }

    def "Should send email and check mail"() {
        when:
            messageService.sendEmailAndCheckMail()
        then:
            1 * emailService.checkMail()
            //1 * emailService.sendMail() - z metody setup()
    }

    def "Should send email and check mail - fail"() {
        when:
            messageService.sendEmailAndCheckMail()
        then:
            1 * emailService.checkMail()
            1 * emailService.sendMail()
            //1 * emailService.sendMail() - z metody setup(), powód błędu
    }

    class MessageService {

        EmailService emailService

        void sendEmailAndCheckMail() {
            emailService.sendMail()
            emailService.checkMail()
        }

    }

    interface EmailService {
        void sendMail()

        void checkMail()
    }

}