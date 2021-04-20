package com.example.demo.model.Dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Double price;

    private Integer quantity;

    private String img;

    public ProductDto() {
    }

    public ProductDto(String name, Double price, Integer quantity, String img) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }
}

