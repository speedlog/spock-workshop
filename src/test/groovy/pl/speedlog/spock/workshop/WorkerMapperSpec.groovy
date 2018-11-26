package pl.speedlog.spock.workshop

import spock.lang.Specification

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class WorkerMapperSpec extends Specification {

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

    def "Should map Developer to WorkerDTO (using with)"() {
        when:
            def result = workerMapper.developerToWorkerDTO(developer)
        then:
            result.additionalInfo == "Languages: Java, Groovy, Python"
            checkPersonalInfoUsingWith(result, developer.getPersonalInfo())
    }

    def "Should map Admin to WorkerDTO (using with)"() {
        when:
            def result = workerMapper.adminToWorkerDTO(admin)
        then:
            result.additionalInfo == "Systems: MacOs, Windows, Linux"
            checkPersonalInfoUsingWith(result, admin.getPersonalInfo())
    }

    def "Should map Developer to WorkerDTO (using verifyAll)"() {
        when:
            def result = workerMapper.developerToWorkerDTO(developer)
        then:
            result.additionalInfo == "Languages: Java, Groovy, Python"
            checkPersonalInfoUsingVerifyAll(result, developer.getPersonalInfo())
    }

    def "Should map Admin to WorkerDTO (using verifyAll)"() {
        when:
            def result = workerMapper.adminToWorkerDTO(admin)
        then:
            result.additionalInfo == "Systems: MacOs, Windows, Linux"
            checkPersonalInfoUsingVerifyAll(result, admin.getPersonalInfo())
    }

    void checkPersonalInfoUsingWith(def result, PersonalInfo expectedInfo) {
        with(result) {
            result.name == expectedInfo.name
            result.surname == expectedInfo.surname
            result.phone == expectedInfo.phone
        }
    }

    void checkPersonalInfoUsingVerifyAll(def result, PersonalInfo expectedInfo) {
        verifyAll(result) {
            result.name == expectedInfo.name
            result.surname == expectedInfo.surname
            result.phone == expectedInfo.phone
        }
    }

}
