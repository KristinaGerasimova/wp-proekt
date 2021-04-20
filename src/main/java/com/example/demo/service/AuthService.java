package com.example.demo.service;

import com.example.demo.model.Hall;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    User login(String username, String password);

    UserDetails loadUserByUsername(String username);

    User register(String username, String password, String repeatPassword, String name, String surname, Role role, Hall hall);
}
