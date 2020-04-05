package com.sportapp.demo.exception;


public class UserExistException extends RuntimeException {

    public UserExistException(){
        super("User not found");
    };


}
