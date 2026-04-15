package com.max.avia2spring.controller;

import com.max.avia2spring.model.Flight;
import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.repository.FlightRepository;
import com.max.avia2spring.repository.CrewMemberRepository;
import com.max.avia2spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/assign")
@RequiredArgsConstructor
public class AssignController {

    private final FlightRepository flightRepository;
    private final CrewMemberRepository crewRepository;
    private final UserRepository userRepository;


    @PostMapping("/addJoin")
    public String assignCrewToFlight(@RequestParam Long id1, @RequestParam Long id2) {
        Optional<Flight> flight = flightRepository.findById(id1);
        Optional<CrewMember> crewMember = crewRepository.findById(id2);
        if (flight.isPresent() && crewMember.isPresent()) {
            flight.get().getCrewMembers().add(crewMember.get());
            flightRepository.save(flight.get());
        }
        return "redirect:/assign";
    }

    @PostMapping("/deleteJoin")
    public String removeCrewFromFlight(@RequestParam Long id1, @RequestParam Long id2) {
        Optional<Flight> flight = flightRepository.findById(id1);
        Optional<CrewMember> crewMember = crewRepository.findById(id2);
        if (flight.isPresent() && crewMember.isPresent()) {
            flight.get().getCrewMembers().remove(crewMember.get());
            flightRepository.save(flight.get());
        }
        return "redirect:/assign";
    }

    @GetMapping
    public String showAssignPage(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        model.addAttribute("crewMembers", crewRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "assign";
    }

}