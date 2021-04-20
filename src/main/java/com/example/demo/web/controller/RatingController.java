package com.example.demo.web.controller;

import com.example.demo.model.Hall;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.HallService;
import com.example.demo.service.RatingService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RatingController {
    public final RatingService ratingService;
    public final HallService hallService;
    public final AuthService authService;


    public RatingController(RatingService ratingService, HallService hallService, AuthService authService) {
        this.ratingService = ratingService;
        this.hallService = hallService;
        this.authService = authService;
    }

    @GetMapping("/rating/{id}/create")
    public String createGradePage(@PathVariable Long id, Model model) {

        Hall hall = this.hallService.findById(id).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = (User) this.authService.loadUserByUsername(username);

        List<Rating> ratings = this.ratingService.findAllByHallId(id);

        int numA = this.ratingService.findAllByGradeAndHall_Id('A', hall.getId()).size();
        int numB = this.ratingService.findAllByGradeAndHall_Id('B', hall.getId()).size();
        int numC = this.ratingService.findAllByGradeAndHall_Id('C', hall.getId()).size();
        int numD = this.ratingService.findAllByGradeAndHall_Id('D', hall.getId()).size();
        int numE = this.ratingService.findAllByGradeAndHall_Id('E', hall.getId()).size();
        int numF = this.ratingService.findAllByGradeAndHall_Id('F', hall.getId()).size();


        model.addAttribute("numA", String.valueOf(numA));
        model.addAttribute("numB", String.valueOf(numB));
        model.addAttribute("numC", String.valueOf(numC));
        model.addAttribute("numD", String.valueOf(numD));
        model.addAttribute("numE", String.valueOf(numE));
        model.addAttribute("numF", String.valueOf(numF));

        model.addAttribute("hall", hall);
        model.addAttribute("user", user);
        model.addAttribute("ratings", ratings);
        model.addAttribute("bodyContent", "rating");
        return "master-template";
    }

    @PostMapping("/rating/{id}/add")
    public String addGrade(@PathVariable Long id,
                           @RequestParam String username,
                           @RequestParam String grade) {
        this.ratingService.create(username, id, grade);
        return "redirect:/rating/{id}/create";
    }
}
