package com.example.demo.model.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username){
        super (String.format("User with username: %s already exists", username));
    }
}
