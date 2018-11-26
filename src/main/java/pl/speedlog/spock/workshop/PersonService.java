package pl.speedlog.spock.workshop;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class PersonService {

    private final PersonDAO personDAO;
    private final InformationService informationService;

    public PersonService(PersonDAO personDAO, InformationService informationService) {
        this.personDAO = personDAO;
        this.informationService = informationService;
    }

    /**
     * Change personal info and send sms with information about what was changed.
     * @param id person id
     * @param name new name
     * @param surname new surname
     * @return true if sms was send
     */
    public boolean changePersonInfo(Long id, String name, String surname) {
        Person person = personDAO.findById(id);
        if (person == null) {
            throw new IllegalStateException(String.format("There is no person with ID: %d", id));
        }
        boolean changedName = false;
        boolean changedSurname = false;

        if (name != null && !name.equals(person.getName())) {
            person.setName(name);
            changedName = true;
        }

        if (surname != null && !surname.equals(person.getSurname())) {
            person.setSurname(surname);
            changedSurname = true;
        }

        boolean smsWasSend = false;
        if (person.getPhone() != null) {
            String message = getMessage(changedName, changedSurname);
            smsWasSend = informationService.sendInformation(person.getPhone(), message);
        }

        return smsWasSend;
    }

    private String getMessage(boolean changedName, boolean changedSurname) {
        if (changedName && changedSurname) {
            return "Name and surname has changed";
        }
        if (changedName) {
            return "Name has changed";
        }
        else {
            return "Surname has changed";
        }
    }
}

interface InformationService {

    boolean sendInformation(String phone, String msg);

}

interface PersonDAO {

    Person findById(Long id);
}

@Getter
@Setter
class Person {
    Long id;
    String name;
    String surname;
    String phone;

}
