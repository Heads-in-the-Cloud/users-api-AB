package com.smoothstack.utopia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFound(final NotFoundException exception) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidUpdateIdException.class)
    public ResponseEntity<Object> invalidUpdateId(final InvalidUpdateIdException exception) {
        return new ResponseEntity<>("Route Id and Entity Id do not match", HttpStatus.BAD_REQUEST);
    }
}
