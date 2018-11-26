package pl.speedlog.spock.workshop

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class PersonServiceSpec extends Specification {

    def personDAO = Mock(PersonDAO)
    def informationService = Mock(InformationService)
    def personService = new PersonService(personDAO, informationService)

    def "Should throw IllegalStateException when person doesn't exists"() {
        given:
            personDAO.findById(1) >> null
        when:
            personService.changePersonInfo(1, "John", "Rambo")
        then:
            def exception = thrown(IllegalStateException)
            exception.message == "There is no person with ID: 1"
    }

    def "Shouldn't change person info and shouldn't send sms, when name/surname is null"() {
        given:
            def person = new Person(1, "Mark", "Anon", "555")
            personDAO.findById(1) >> person
        when:
            def smsSend = personService.changePersonInfo(1, null, null)
        then:
            0 * person.setName(_)
            0 * person.setSurname(_)
            0 * informationService.sendInformation(_, _)
            smsSend == false
    }

    def "Shouldn't change person info, when name/surname doesn't differ"() {
        given:
            def person = new Person(1, "John", "Rambo", "555")
            personDAO.findById(1) >> person
        when:
            def smsSend = personService.changePersonInfo(1, "John", "Rambo")
        then:
            0 * person.setName(_)
            0 * person.setSurname(_)
            0 * informationService.sendInformation(_, _)
            smsSend == false
    }

    @Unroll
    def "Should send only one SMS and only if person has phone number"() {
        given:
            def person = new Person(1, oldName, oldSurname, phoneNumber)
            personDAO.findById(1) >> person
        when:
            def result = personService.changePersonInfo(1, newName, newSurname)
        then:
            result == smsWasSend
            (smsWasSend ? 1 : 0) * informationService.sendInformation(sendTo, smsText) >> true
        where:
            oldName | newName | oldSurname | newSurname | phoneNumber | sendTo | smsText                         | smsWasSend
            "John"  | "John"  | "Rambo"    | "Bravo"    | "555"       | "555"  | "Surname has changed"           | true
            "John"  | "John"  | "Rambo"    | "Bravo"    | null        | _      | _                               | false
            "John"  | "Mark"  | "Rambo"    | "Rambo"    | "555"       | "555"  | "Name has changed"              | true
            "John"  | "Mark"  | "Rambo"    | "Rambo"    | null        | _      | _                               | false
            "John"  | "Mark"  | "Rambo"    | "Bravo"    | "555"       | "555"  | "Name and surname has changed"  | true
            "John"  | "Mark"  | "Rambo"    | "Bravo"    | null        | _      | _                               | false
    }
}
