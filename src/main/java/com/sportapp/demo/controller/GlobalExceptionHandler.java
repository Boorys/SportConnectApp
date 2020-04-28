package com.sportapp.demo.controller;

import com.sportapp.demo.exception.MainTypSportExistException;
import com.sportapp.demo.exception.UserExistException;
import org.json.JSONException;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<VndErrors> userExist(UserExistException e)
    {
        return error(e, HttpStatus.CONFLICT,"User not found");
    }


    @ExceptionHandler(MainTypSportExistException.class)
    public ResponseEntity<VndErrors> mainTypSportExistExist(MainTypSportExistException e)
    {
        return error(e, HttpStatus.CONFLICT,"MainTypSportExist not found");
    }




    private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());

        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }



}
