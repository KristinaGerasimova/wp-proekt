package com.example.demo.model.exception;

public class HallNotFoundException extends RuntimeException{

    public HallNotFoundException(Long id) {
        super(String.format("Hall with id: %d was not found", id));
    }
}
