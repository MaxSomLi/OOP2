package com.max.avia2spring.controller;

import com.max.avia2spring.model.Flight;
import com.max.avia2spring.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/flights")
@RequiredArgsConstructor
public class MyFlightsController {

    private final FlightRepository flightRepository;

    @GetMapping
    public String showFlights(Model model) {
        String currentUsername = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        List<Flight> flights = flightRepository.findAllByCrewMembersName(currentUsername);
        model.addAttribute("myFlights", flights);
        return "flights";
    }
}