package com.example.demo.model.exception;

public class InvalidUsernameOrPasswordException extends RuntimeException{

    public InvalidUsernameOrPasswordException(){
        super("Invalid username or password exception.");
    }
}
