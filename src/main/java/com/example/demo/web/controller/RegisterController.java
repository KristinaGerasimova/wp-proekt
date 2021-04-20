package com.example.demo.web.controller;


import com.example.demo.model.Hall;
import com.example.demo.model.enumerations.Role;
import com.example.demo.model.exception.InvalidArgumentsException;
import com.example.demo.model.exception.PasswordDoNotMatchException;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role,
                           @RequestParam Hall hall) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, role,hall);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}


