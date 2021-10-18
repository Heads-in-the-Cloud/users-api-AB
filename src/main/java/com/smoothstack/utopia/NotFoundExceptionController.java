package com.smoothstack.utopia;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> exception(final NotFoundException exception) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
