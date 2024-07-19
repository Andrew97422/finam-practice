package ru.finam.backend.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.finam.backend.exceptions.IllegalPageLimitException;
import ru.finam.backend.exceptions.PageIndexOutOfBoundException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler( PageIndexOutOfBoundException.class )
    protected ResponseEntity<Object> handlePageIndexOutOfBoundEx(RuntimeException ex, WebRequest request) {
        String msg = ex.getMessage();
        ApiError apiError = new ApiError(msg, msg);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( IllegalPageLimitException.class )
    protected ResponseEntity<Object> handleIllegalPageLimitEx(RuntimeException ex, WebRequest request) {
        String msg = ex.getMessage();
        ApiError apiError = new ApiError(msg, msg);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}