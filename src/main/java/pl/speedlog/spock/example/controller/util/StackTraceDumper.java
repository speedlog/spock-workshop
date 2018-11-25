package pl.speedlog.spock.example.controller.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * From: https://github.com/rafal-glowinski/mvctest-spock
 */
final class StackTraceDumper {

    private StackTraceDumper() {
    }

    static String dumpStackTrace(Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));

        return stringWriter.toString();
    }
}