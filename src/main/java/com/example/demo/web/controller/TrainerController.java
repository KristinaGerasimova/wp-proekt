package com.example.demo.web.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Trainer;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping
    public String getAllTrainers(Model model)
    {
        List<Trainer> trainerList = this.trainerService.findAll();

        model.addAttribute("trainers", trainerList);
        model.addAttribute("bodyContent", "trainers");

        return "master-template";
    }

    @PostMapping("/add")
    public String addTrainer(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam String img)
    {
        this.trainerService.save(name, address, img);

        return "redirect:/trainers";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        this.trainerService.deleteById(id);
        return "redirect:/trainers";
    }
}
