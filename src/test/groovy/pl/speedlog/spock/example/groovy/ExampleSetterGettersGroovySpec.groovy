package pl.speedlog.spock.example.groovy

import spock.lang.Specification

/**
 * Przykład używania getterów i setterów w Groovy.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class ExampleSetterGettersGroovySpec extends Specification {

	def "Should use explicit defined setters and getters"() {
		given:
			def exampleClass = new GroovyClassWithSetterAndGetters()
		when: "set values using setters"
			exampleClass.stringField = "example string"
			exampleClass.integerField = 1
		then: "check values using getters"
			exampleClass.stringField == "example string"
			exampleClass.integerField == 1
	}

	def "Should use automatic generated setters and getters"() {
		given:
			def exampleClass = Spy(GroovyClassWithoutSetterAndGetters)
		when: "set values using setters"
			exampleClass.stringField = "example string"
			exampleClass.integerField = 1
		and:
			exampleClass.stringField
			exampleClass.integerField
		then: "check if used automatic generated setter and getters"
			1 * exampleClass.setStringField(_)
			1 * exampleClass.setIntegerField(_)
			1 * exampleClass.getStringField()
			1 * exampleClass.getIntegerField()
	}
}

class GroovyClassWithSetterAndGetters {

	String stringField
	Integer integerField

	String getStringField() {
		println "Using getStringField()"
		return stringField
	}

	void setStringField(String stringField) {
		println "Using setStringField()"
		this.stringField = stringField
	}

	Integer getIntegerField() {
		println "Using getIntegerField()"
		return integerField
	}

	void setIntegerField(Integer integerField) {
		println "Using setIntegerField()"
		this.integerField = integerField
	}
}

class GroovyClassWithoutSetterAndGetters {

	String stringField
	Integer integerField

}