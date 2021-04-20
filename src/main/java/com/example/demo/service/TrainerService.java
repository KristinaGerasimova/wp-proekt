package com.example.demo.service;

import com.example.demo.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    Optional<Trainer> findById(Long id);
    List<Trainer> findAll();
    Optional<Trainer> save(String name, String address, String img);
    void deleteById(Long id);

}
