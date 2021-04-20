package com.example.demo.model.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id) {
        super(String.format("Category with id: %d was not found", id));
    }
}
