package com.example.demo.model.exception;

public class TrainerNotFoundException extends RuntimeException{

    public TrainerNotFoundException(Long id) {
        super(String.format("Trainer with id: %d was not found", id));
    }
}
