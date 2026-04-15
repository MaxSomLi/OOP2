package com.max.avia2spring.controller;

import com.max.avia2spring.model.User;
import com.max.avia2spring.repository.UserRepository;
import com.max.avia2spring.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/addUser")
    public String register(@ModelAttribute LoginRequest request) {
        User newUser = new User();
        newUser.setName(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
        return "redirect:/login";
    }

}