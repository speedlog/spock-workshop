package pl.speedlog.spock.workshop;

import java.util.List;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class MessageService {

    private final SmsService smsService;

    public MessageService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void sendSms(List<String> phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber.size() >= 5) {
            throw new IllegalArgumentException("List of phone numbers can't be empty or greather than 5");
        }

        phoneNumber.stream().filter(number -> !number.startsWith("5")).forEach(smsService::sendSms);
    }
}

interface SmsService {

    void sendSms(String phone);
}
