package pl.speedlog.spock.example

import org.spockframework.mock.TooManyInvocationsError
import spock.lang.FailsWith
import spock.lang.Specification

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

    def "Should send sms, email and sms - the same order as in code"() {
        when:
            messageService.sendTwoSmsAndOneMail()
        then:
            1 * smsService.sendSms() // dopasowanie do A
            1 * emailService.sendMail() // dopasowanie do B
            1 * smsService.sendSms() // dopasowanie do C
    }

    def "Should send sms, email and sms - different order than in code"() {
        when:
            messageService.sendTwoSmsAndOneMail()
        then:
            // kolejność deklaracji nie musi odpowiadać kolejności wywołań
            // przykład jak sprawdzić kolejność wywołań:
            // patrz: MockInvocationOrderExampleSpec
            1 * emailService.sendMail() // dopasowanie do B
            2 * smsService.sendSms() // dopasowanie do A i C
    }

    @FailsWith(TooManyInvocationsError)
    def "Should send sms, email and bulk sms - fail, because of matching"() {
        when:
            messageService.sendSmsAndBulkSms()
        then:
            1 * smsService._             // dopasowanie do D
            1 * smsService.sendSms()     // dopasowanie do E - liczba wywołań się nie zgadza
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
            smsService.sendSms() // D
            smsService.sendBulkSms() // E
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
