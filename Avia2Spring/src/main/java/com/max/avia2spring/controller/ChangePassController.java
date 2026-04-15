package com.max.avia2spring.controller;

import com.max.avia2spring.repository.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@RequestMapping("/pass")
@RequiredArgsConstructor
public class ChangePassController {

    private final CrewMemberRepository crewRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String showPassPage() {
        return "pass";
    }

    @PostMapping("/changePass")
    public String changePassword(@RequestParam("old") String oldPass, @RequestParam("new") String newPass, @RequestParam("con") String confirmPass) {
        String currentUserName = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        if (newPass == null || !newPass.equals(confirmPass)) {
            return "redirect:/pass?error";
        }
        AtomicBoolean flag = new AtomicBoolean(false);
        crewRepository.findByName(currentUserName).ifPresent(member -> {
            String encodedPasswordInDb = member.getPassword();
            if (encodedPasswordInDb != null) {
                if (encodedPasswordInDb.startsWith("$2a$") && !encodedPasswordInDb.startsWith("{bcrypt}")) {
                    encodedPasswordInDb = "{bcrypt}" + encodedPasswordInDb;
                } else if (!encodedPasswordInDb.startsWith("{")) {
                    encodedPasswordInDb = "{noop}" + encodedPasswordInDb;
                }
            }
            if (passwordEncoder.matches(oldPass, encodedPasswordInDb)) {
                member.setPassword(passwordEncoder.encode(newPass));
                crewRepository.save(member);
                flag.set(true);
            }
        });
        return (flag.get()) ? "redirect:/assign" : "redirect:/pass?error";
    }
}