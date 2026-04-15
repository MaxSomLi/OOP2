package com.max.avia2spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/login")
public class AuthController {

    @GetMapping
    public String loginPage(@RequestParam(value = "error", required = false) String error) {
        return "login";
    }
}