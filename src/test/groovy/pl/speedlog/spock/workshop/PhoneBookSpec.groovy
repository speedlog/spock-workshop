package pl.speedlog.spock.workshop

import spock.lang.Specification

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class PhoneBookSpec extends Specification {

    def phoneBook = new PhoneBook()

    def "Should add phone to phone book"() {
        when:
            phoneBook.addPhone("500 600 700", "John")
        then:
            phoneBook.getNameByPhone("500 600 700") == "John"
    }

    def "Should throw IllegalArgumentException when pass empty phone"() {
        when:
            phoneBook.addPhone("", "John")
        then:
            def exception = thrown(IllegalArgumentException)
            exception.message == "Phone can't be empty!"
    }

    def "Should throw IllegalArgumentException when pass empty name"() {
        when:
            phoneBook.addPhone("500 600 700", "")
        then:
            def exception = thrown(IllegalArgumentException)
            exception.message == "Name can't be empty!"
    }

}
