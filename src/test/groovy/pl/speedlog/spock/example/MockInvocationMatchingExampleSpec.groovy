package pl.speedlog.spock.example

import org.spockframework.mock.TooManyInvocationsError
import spock.lang.FailsWith;
import spock.lang.Specification;

/**
 * Przykład dopasowania interakcji.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInvocationMatchingExampleSpec extends Specification {

    def emailService = Mock(EmailService)
    def smsService = Mock(SmsService)
    def messageService = new MessageService(
            emailService: emailService,
            smsService: smsService
    )

    def "Should send sms, email and sms"() {
        when:
            messageService.sendTwoSmsAndOneMail()
        then:
            // kolejność deklaracji nie musi odpowiadać kolejności wywołań
            // przykład jak sprawdzić kolejność wywołań:
            // patrz: MockInvocationOrderExampleSpec
            // B
            1 * emailService.sendMail()
            // A
            // C
            2 * smsService.sendSms()
    }

    @FailsWith(TooManyInvocationsError)
    def "Should send sms, email and bulk sms - fail, because of matching"() {
        when:
            messageService.sendSmsAndBulkSms()
        then:
            // A
            // B - liczba wywołań się nie zgadza
            1 * smsService._
            1 * smsService.sendSms()
    }

    class MessageService {

        EmailService emailService
        SmsService smsService

        void sendTwoSmsAndOneMail() {
            smsService.sendSms() // A
            emailService.sendMail() // B
            smsService.sendSms() // C
        }

        void sendSmsAndBulkSms() {
            smsService.sendSms() // A
            smsService.sendBulkSms() // B
        }

    }

    interface EmailService {
        void sendMail()
    }

    interface SmsService {
        void sendSms()

        void sendBulkSms()
    }

}
