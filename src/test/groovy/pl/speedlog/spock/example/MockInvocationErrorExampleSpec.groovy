package pl.speedlog.spock.example

import org.spockframework.mock.TooFewInvocationsError
import org.spockframework.mock.TooManyInvocationsError
import spock.lang.FailsWith
import spock.lang.Specification

/**
 * Przykład błędów interakcji.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInvocationErrorExampleSpec extends Specification {

    def emailService = Mock(EmailService)
    def messageService = new MessageService(emailService: emailService)


    @FailsWith(TooFewInvocationsError)
    def "Too few calls - should fail"() {
        when:
            messageService.sendMail()
        then:
            1 * emailService.checkMail()
    }

    @FailsWith(TooManyInvocationsError)
    def "Too many calls - should fail"() {
        when:
            messageService.sendMail()
            messageService.sendMail()
        then:
            1 * emailService.sendMail()
    }

    @FailsWith(TooFewInvocationsError)
    def "Wrong parameters - should fail"() {
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

    }

    interface EmailService {
        void sendMail()

        void checkMail()

        void sendMail(String msg)
    }

}