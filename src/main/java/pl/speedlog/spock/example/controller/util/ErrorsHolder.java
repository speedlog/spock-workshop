package pl.speedlog.spock.example.controller.util;

import java.util.Arrays;
import java.util.List;

/**
 * From: https://github.com/rafal-glowinski/mvctest-spock
 */
public class ErrorsHolder {

    private final List<Error> errors;

    public ErrorsHolder(List<Error> errors) {
        this.errors = errors;
    }

    public ErrorsHolder(Error... errors) {
        this.errors = Arrays.asList(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}