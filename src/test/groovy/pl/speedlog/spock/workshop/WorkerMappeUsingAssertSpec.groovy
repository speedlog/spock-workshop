package pl.speedlog.spock.workshop

import spock.lang.Specification

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WorkerMappeUsingAssertSpec extends Specification {

    def workerMapper = new WorkerMapper()

    def developer = new Developer(
            "Java, Groovy, Python",
            new PersonalInfo(
                    "John",
                    "Rambo",
                    "555-666-777"
            )
    )

    def admin = new Admin(
            "MacOs, Windows, Linux",
            new PersonalInfo(
                    "Lara",
                    "Croft",
                    "555-000-555"
            )
    )

    def "Should map Developer to WorkerDTO (using assert)"() {
        when:
            def result = workerMapper.developerToWorkerDTO(developer)
        then:
            result.additionalInfo == "Languages: Java, Groovy, Python"
            checkPersonalInfoUsingAssert(result, developer.getPersonalInfo())
    }

    def "Should map Admin to WorkerDTO (using assert)"() {
        when:
            def result = workerMapper.adminToWorkerDTO(admin)
        then:
            result.additionalInfo == "Systems: MacOs, Windows, Linux"
            checkPersonalInfoUsingAssert(result, admin.getPersonalInfo())
    }

    void checkPersonalInfoUsingAssert(def result, PersonalInfo expectedInfo) {
        assert result.name == expectedInfo.name
        assert result.surname == expectedInfo.surname
        assert result.phone == expectedInfo.phone
    }

}
