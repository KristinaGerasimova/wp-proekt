package com.example.demo.service;

import com.example.demo.model.Dto.ProductDto;
import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price, Integer quantity, String img);

    Optional<Product> save(ProductDto productDto);

    Optional<Product> edit(Long id, String name, Double price, Integer quantity, String img);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);

}
