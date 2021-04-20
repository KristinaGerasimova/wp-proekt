package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description, String img);

    Category update(String name, String description, String img);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);


    void deleteById(Long id);
}
