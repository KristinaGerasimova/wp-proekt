package com.example.demo.web.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Hall;
import com.example.demo.model.Trainer;
import com.example.demo.service.CategoryService;
import com.example.demo.service.HallService;
import com.example.demo.service.TrainerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;
    private final CategoryService categoryService;
    private final TrainerService trainerService;

    public HallController(HallService hallService, CategoryService categoryService, TrainerService trainerService) {
        this.hallService = hallService;
        this.categoryService = categoryService;
        this.trainerService = trainerService;
    }

    @GetMapping
    public String getHallPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Hall> halls = this.hallService.findAll();
        model.addAttribute("halls", halls);
        model.addAttribute("bodyContent", "halls");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHall(@PathVariable Long id){
        this.hallService.deleteById(id);
        return "redirect:/halls";
    }

    @GetMapping("/edit-form/{id}")
    public String editHallPage(@PathVariable Long id, Model model){
        if(this.hallService.findById(id).isPresent()){
            Hall hall = this.hallService.findById(id).get();
            List<Trainer> trainers = this.trainerService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("trainers", trainers);
            model.addAttribute("categories", categories);
            model.addAttribute("hall", hall);
            model.addAttribute("bodyContent", "add-hall");
            return "master-template";
        }
        return "redirect:/halls?error=HallNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addHallPage(Model model){
        List<Trainer> trainers = this.trainerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("trainers", trainers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-hall");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveHall(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category,
            @RequestParam Long trainer,
            @RequestParam String img){
        if(id!=null){
            this.hallService.edit(id, name, price, quantity, category, trainer, img);
        }
        else{
            this.hallService.save(name, price, quantity, category ,trainer, img);
        }
        return "redirect:/halls";
    }
}
