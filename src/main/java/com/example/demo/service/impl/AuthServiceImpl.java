package com.example.demo.service.impl;

import com.example.demo.model.Hall;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.Role;
import com.example.demo.model.exception.InvalidArgumentsException;
import com.example.demo.model.exception.InvalidUserCredentialsException;
import com.example.demo.model.exception.InvalidUsernameOrPasswordException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw  new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role, Hall hall) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
//        if (!password.equals(repeatPassword))
//            throw new PasswordsDoNotMatchException();
//        if(this.userRepository.findByUsername(username).isPresent())
//            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,role, hall);
        return userRepository.save(user);
    }
}
