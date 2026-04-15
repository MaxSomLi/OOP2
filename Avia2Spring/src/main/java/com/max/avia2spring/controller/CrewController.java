package com.max.avia2spring.controller;

import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.repository.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assign")
@RequiredArgsConstructor
public class CrewController {

    private final CrewMemberRepository crewRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/addCrew")
    public String addCrewMember(@ModelAttribute CrewMember crewMember) {
        if (crewMember.getPassword() != null) {
            crewMember.setPassword(passwordEncoder.encode(crewMember.getPassword()));
        }
        crewRepository.save(crewMember);
        return "redirect:/assign";
    }

    @PostMapping("/deleteCrew")
    public String deleteCrewMember(@RequestParam Long id) {
        crewRepository.deleteById(id);
        return "redirect:/assign";
    }

}