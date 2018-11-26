package pl.speedlog.spock.workshop;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class PhoneBook {

    /**
     * Key is phone number.
     * Value is a name.
     */
    private HashMap<String, String> book = new HashMap<>();

    public void addPhone(String phone, String name) {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException("Phone can't be empty!");
        }
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name can't be empty!");
        }
        book.put(phone, name);
    }

    public String getNameByPhone(String phone) {
        return book.get(phone);
    }

}
