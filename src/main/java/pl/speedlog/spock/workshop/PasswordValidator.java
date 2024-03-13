package pl.speedlog.spock.workshop;

import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

    static final String CAPITALIZED_LETTERS = "AB";
    static final String DIGITS = "01";
    static final String SPECIAL_CHARACTERS = "!?";

    /**
     * Password must have:
     * one char from set: AB
     * one char from set: !?
     * one char from set: 01
     * length exactly 3
     *
     * @param password password
     * @return true if password is strong enough
     */
    boolean isPasswordStrongEnough(String password) {
        if (password == null || password.length() != 3) {
            return false;
        }
        List<String> splittedPassword = Arrays.asList(password.split(""));

        return splittedPassword.stream().anyMatch(CAPITALIZED_LETTERS::contains)
                && splittedPassword.stream().anyMatch(DIGITS::contains)
                && splittedPassword.stream().anyMatch(SPECIAL_CHARACTERS::contains);
    }
}
