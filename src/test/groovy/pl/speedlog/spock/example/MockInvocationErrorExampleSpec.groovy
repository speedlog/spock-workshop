package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład błędów interakcji.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInvocationErrorExampleSpec extends Specification {

    def emailService = Mock(EmailService)
    def messageService = new MessageService(emailService: emailService)


    def "Too few calls"() {
        when:
            messageService.sendMail()
        then:
            1 * emailService.checkMail()
    }

    def "Too many calls"() {
        when:
            messageService.sendAndCheckMail()
        then:
            2 * emailService.sendMail()
    }

    def "Wrong parameters"() {
        when:
            messageService.sendMailWithMessage("abc")
        then:
            1 * emailService.sendMail("hello")
    }

    class MessageService {

        EmailService emailService

        void sendMail() {
            emailService.sendMail()
        }

        void sendMailWithMessage(String msg) {
            emailService.sendMail(msg)
        }

        void sendAndCheckMail() {
            emailService.sendMail()
            emailService.checkMail()
        }

    }

    interface EmailService {
        void sendMail()

        void checkMail()

        void sendMail(String msg)
    }

}