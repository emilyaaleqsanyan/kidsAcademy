package am.itspace.kidsacademy.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IOException.class)
    public String ioExceptionHandler() {
        return "admin/error";
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public String fileNotFoundExceptionHandler() {

        return "admin/error";
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public String noResourceExceptionHandler(NoResourceFoundException ex) {
        return "admin/error";
    }

    @ExceptionHandler(value = OwnCustomException.class)
    public String ownExceptionHandler(OwnCustomException ex) {

        return "admin/error";
    }
}
