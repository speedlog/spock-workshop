package pl.speedlog.spock.example;

import spock.lang.Specification;

/**
 * Przykład weryfikacji wywołań mock'a w określonej kolejności.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MockInvocationOrderExampleSpec extends Specification {

    def smsService = Mock(SmsService)
    def messageService = new MessageService(
            smsService: smsService
    )

    def "Should validate number before sending SMS"() {
        when:
            messageService.sendSms("555")
        then:
            1 * smsService.checkPhoneNumber("555")
        then:
            1 * smsService.sendSms("555")
    }

    class MessageService {

        SmsService smsService

        void sendSms(String phoneNumber) {
            smsService.checkPhoneNumber(phoneNumber)
            smsService.sendSms(phoneNumber)
        }
    }

    interface SmsService {

        void checkPhoneNumber(String number)

        void sendSms(String number)
    }

}
