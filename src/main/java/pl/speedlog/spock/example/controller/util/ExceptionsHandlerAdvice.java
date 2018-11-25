package pl.speedlog.spock.example.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * From: https://github.com/rafal-glowinski/mvctest-spock
 */
@ControllerAdvice
public class ExceptionsHandlerAdvice {

    private final ExceptionMapperHelper mapperHelper = new ExceptionMapperHelper();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsHolder> handleException(MethodArgumentNotValidException exception) {
        ErrorsHolder errors = new ErrorsHolder(mapperHelper.errorsFromBindResult(exception, exception.getBindingResult()));
        return mapperHelper.mapResponseWithoutLogging(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

