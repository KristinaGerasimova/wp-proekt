package com.example.demo.web.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(Model model)
    {
        List<Category> categoryList = this.categoryService.listCategories();

        model.addAttribute("categories", categoryList);
        model.addAttribute("bodyContent", "categories");

        return "master-template";
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String img)
    {
        this.categoryService.create(name,description,img);

        return "redirect:/categories";
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteCategory(@PathVariable Long id){
        this.categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
