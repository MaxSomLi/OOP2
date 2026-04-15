package com.max.avia2spring.controller;

import com.max.avia2spring.model.Flight;
import com.max.avia2spring.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assign")
@RequiredArgsConstructor
public class FlightController {

    private final FlightRepository flightRepository;

    @PostMapping("/addFlight")
    public String addFlight(@ModelAttribute Flight flight) {
        flightRepository.save(flight);
        return "redirect:/assign";
    }

    @PostMapping("/deleteFlight")
    public String deleteFlight(@RequestParam Long id) {
        flightRepository.deleteById(id);
        return "redirect:/assign";
    }

}