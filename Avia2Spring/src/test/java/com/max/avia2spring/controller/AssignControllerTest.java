package com.max.avia2spring.controller;

import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.model.Flight;
import com.max.avia2spring.repository.CrewMemberRepository;
import com.max.avia2spring.repository.FlightRepository;
import com.max.avia2spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightRepository flightRepository;

    @MockitoBean
    private CrewMemberRepository crewRepository;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles = "2")
    void testShowAssignPage() throws Exception {
        mockMvc.perform(get("/assign"))
                .andExpect(status().isOk())
                .andExpect(view().name("assign"))
                .andExpect(model().attributeExists("flights", "crewMembers", "users"));
        verify(flightRepository).findAll();
    }

    @Test
    @WithMockUser(roles = "2")
    void testAddJoinSuccess() throws Exception {
        Flight flight = new Flight();
        flight.setId(1L);
        CrewMember crew = new CrewMember();
        crew.setId(2L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(crewRepository.findById(2L)).thenReturn(Optional.of(crew));
        mockMvc.perform(post("/assign/addJoin")
                .param("id1", "1")
                .param("id2", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    @WithMockUser(roles = "2")
    void testAddJoinWithInvalidIds_ShouldNotCrash() throws Exception {
        when(flightRepository.findById(-1L)).thenReturn(Optional.empty());
        mockMvc.perform(post("/assign/addJoin")
                .param("id1", "-1")
                .param("id2", "2"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/assign"));
        verify(flightRepository, times(0)).save(any());
    }

    @Test
    @WithMockUser(roles = "2")
    void testDeleteJoinSuccess() throws Exception {
        CrewMember crew = new CrewMember();
        crew.setId(2L);
        Flight flight = new Flight();
        flight.setId(1L);
        flight.getCrewMembers().add(crew);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(crewRepository.findById(2L)).thenReturn(Optional.of(crew));
        mockMvc.perform(post("/assign/deleteJoin")
                .param("id1", "1")
                .param("id2", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));
        verify(flightRepository).save(flight);
    }
}