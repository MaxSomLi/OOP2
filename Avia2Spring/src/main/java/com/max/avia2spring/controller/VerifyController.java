package com.max.avia2spring.controller;

import com.max.avia2spring.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assign/verify")
@RequiredArgsConstructor
public class VerifyController {

    private final VerificationService verificationService;

    @PostMapping
    public String verifyUser(@RequestParam Long id, @RequestParam(name = "is_admin", defaultValue = "false") boolean isAdmin, @RequestParam(name = "verify", defaultValue = "false") boolean verify) {
        verificationService.verifyAndPromote(id, isAdmin, verify);
        return "redirect:/assign";
    }
}