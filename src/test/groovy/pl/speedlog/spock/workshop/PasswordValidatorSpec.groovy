package pl.speedlog.spock.workshop

import spock.lang.*

class PasswordValidatorSpec extends Specification {

	def passwordValidator = new PasswordValidator()

	/**
	 3 grupy:
	 [
	 ['A', 'B'],
	 ['1', '2'],
	 ['!', '?']
	 ]
	 */
	static def groups = [
			PasswordValidator.CAPITALIZED_LETTERS.split(""),
			PasswordValidator.DIGITS.split(""),
			PasswordValidator.SPECIAL_CHARACTERS.split("")
	]

	def "Test if strong passwords would pass validation - using combinations"() {
		when:
			println group[0] + group[1] + group[2]
			boolean result = passwordValidator.isPasswordStrongEnough(group[0] + group[1] + group[2])
		then:
			result == true
		where:
			/**
			Zastosowanie metody "combinations" wygeneruje 8 tablic po 3 elementy.
			[
				['A', '1', '!'],
				['A', '1', '?'],
				...
			]

			Co daje nam kombinacje:
		 	A1!
			A1?
			A2!
			A2?
			B1!
			B1?
			B2!
			B2?

			Czemu 8?
			Na poczatku mamy 3 grupy, w każdej po 2 znaki.
			2 * 2 * 2 = 8
			*/
			group << groups.combinations()
	}

	def "Test if strong passwords would pass validation - using combinations with closure"() {
		when:
			println password
			boolean result = passwordValidator.isPasswordStrongEnough(password)
		then:
			result == true
		where:
			/**
			 * Closure zwraca od razu tablicę stringów
			 * [
			 *  'A0!',
			 *  'B0!',
			 *  'A1!'
			 * ]
			 */
			password  << groups.combinations {
				group1, group2, group3 -> group1[0] + group2[0] + group3[0]
			}

	}


	def "Test if strong passwords would pass validation - using permutations"() {
		when:
			def password = group1[0] + group2[0] + group3[0]

			/**
			 Co daje nam kombinacje:
			 A!0
			 !A0
			 0A!
			 0!A
			 A0!
			 !0A
			 */
			println password
			boolean result = passwordValidator.isPasswordStrongEnough(password)
		then:
			result == true
		where:
			/**
			 Zastosowanie metody "permutations" wygeneruje 6 tablic po 3 tablice, które będą permutacjami tablic na wejściu.
			 [
			 	[ ["A", "B"], ["0", "1"], ["!", "?"] ],
			 	[ ["0", "1"], ["A", "B"], ["!", "?"] ],
			    [ ["0", "1"], ["!", "?"], ["A", "B"] ],
			      ...
			 ]

			 Czemu 6? Wyliczamy silnię z liczby grup.
			 3! = 1 * 2 * 3 = 6
			 */
			[group1, group2, group3]  << groups.permutations()
	}


	@Shared passwordsToTest = groups.permutations().stream().map{ groupPermutation -> {
		groupPermutation.combinations { group1, group2, group3 -> group1[0] + group2[0] + group3[0] }
	}}.collect()
	def "Test if strong passwords would pass validation - all posibilities"() {
		when:
			println password
			boolean result = passwordValidator.isPasswordStrongEnough(password)
		then:
			result == true
		where:
			password << passwordsToTest.flatten()
	}

}
