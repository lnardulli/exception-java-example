package com.exception.demo.model.exceptions;

import com.exception.demo.model.YoungResponse;
import com.exception.demo.time.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class ExceptionGlobalResponse {

    YoungResponse result;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<YoungResponse> runtimeException(RuntimeException e) {
        result = new YoungResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<YoungResponse> exception(Exception e) {
        result = new YoungResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), 370, null);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // To handle the specific exceptions which are resulting from this controller
    @ExceptionHandler({YoungAgeException.class})
    public ResponseEntity<YoungResponse> youngNameException(RuntimeException e) {
        YoungResponse result = new YoungResponse(Time.getTime(),
                "[Exception Response] - Exception: " + e.getMessage(), 404, null);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // To handle the specific exceptions which are resulting from this controller
    @ExceptionHandler({PersistenceException.class})
    public ResponseEntity<YoungResponse> youngNotFound(RuntimeException e) {
        YoungResponse result = new YoungResponse(Time.getTime(),
                "[Exception Response] - Exception: " + e.getMessage(), 404, null);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }



}
