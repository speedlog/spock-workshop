package pl.speedlog.spock.workshop;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class PhoneBook {

    /**
     * Key is a phone number.
     * Value is a name.
     */
    private HashMap<String, String> book = new HashMap<>();

    public void addPhone(String phoneNumber, String name) {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("Phone can't be empty!");
        }
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name can't be empty!");
        }
        book.put(phoneNumber, name);
    }

    public String getNameByPhone(String phoneNumber) {
        return book.get(phoneNumber);
    }

}
