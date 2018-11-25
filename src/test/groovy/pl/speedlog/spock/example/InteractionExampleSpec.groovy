package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład sprawdzania interakcji.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class InteractionExampleSpec extends Specification {

    def "Should send SMS with birthday wishes"() {
        given:
            def smsGateway = Mock(SmsGateway)
            def smsSender = new SmsSender(smsGateway: smsGateway)
            def expectedPhoneNumber = "500 500 500"
        when:
            smsSender.sendBirthdaySms(expectedPhoneNumber)
        then:
            1 * smsGateway.sendSms(expectedPhoneNumber, "Happy Birthday!")
    }

    class SmsSender {
        SmsGateway smsGateway

        void sendBirthdaySms(String phoneNumber) {
            smsGateway.sendSms(phoneNumber, "Happy Birthday!")
        }
    }

    interface SmsGateway {
        void sendSms(String phoneNumber, String text)
    }

}