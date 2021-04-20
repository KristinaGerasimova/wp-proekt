package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Hall{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @OneToMany(mappedBy = "hall", fetch = FetchType.EAGER)
   public List<User> users;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Trainer trainer;

    private String img;

    public Hall(){

    }

    public Hall(String name, Double price, Integer quantity, Category category, Trainer trainer, String img){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.trainer = trainer;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
