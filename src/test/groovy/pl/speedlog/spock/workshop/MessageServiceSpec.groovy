package pl.speedlog.spock.workshop

import spock.lang.Specification

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class MessageServiceSpec extends Specification {

    def smsService = Mock(SmsService)
    def messageService = new MessageService(smsService)

    def "Should send sms only for phone numbers that doesn't start with '5'"() {
        given:
            def phoneList = [
                    '011-222-333',
                    '111-222-333',
                    '211-222-333',
                    '311-222-333',
                    '411-222-333',
                    '511-222-333',
                    '611-222-333',
                    '711-222-333',
                    '811-222-333',
                    '911-222-333'
            ]
        when:
            messageService.sendSms(phoneList)
        then:
            9 * smsService.sendSms(_ as String)
    }

    def "Should throw exception when passed more then 10 phone numbers"() {
        given:
            def phoneList = 1..11
        when:
            messageService.sendSms(phoneList)
        then:
            def exception = thrown(IllegalArgumentException)
            exception.message == "List of phone numbers can't be empty or greather than 10"
    }
}
