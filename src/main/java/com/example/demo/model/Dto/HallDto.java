package com.example.demo.model.Dto;

import com.example.demo.model.Category;
import com.example.demo.model.Trainer;
import lombok.Data;

import javax.persistence.*;

@Data
public class HallDto{

    private String name;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long trainer;

    private String img;

    public HallDto(){

    }

    public HallDto(String name, Double price, Integer quantity, Long category, Long trainer, String img){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.trainer = trainer;
        this.img = img;
    }
}
