package com.example.demo.service;

import com.example.demo.model.Dto.HallDto;
import com.example.demo.model.Hall;

import java.util.List;
import java.util.Optional;

public interface HallService {

    List<Hall> findAll();

    Optional<Hall> findById(Long id);

    Optional<Hall> findByName(String name);

    Optional<Hall> save(String name, Double price, Integer quantity, Long category, Long trainer, String img);

    Optional<Hall> save(HallDto hallDto);

    Optional<Hall> edit(Long id, String name, Double price, Integer quantity, Long category, Long trainer, String img);

    Optional<Hall> edit(Long id, HallDto hallDto);

    void deleteById(Long id);

}
