package com.max.avia2spring.controller;

import com.max.avia2spring.model.Flight;
import com.max.avia2spring.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightRepository flightRepository;

    @Test
    @WithMockUser(roles = "2")
    void testAddFlight() throws Exception {
        mockMvc.perform(post("/assign/addFlight")
                        .param("number", "PS123")
                        .param("origin", "Kyiv")
                        .param("destination", "London")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));

        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    @WithMockUser(roles = "2")
    void testDeleteFlight() throws Exception {
        mockMvc.perform(post("/assign/deleteFlight")
                        .param("id", "100")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));

        verify(flightRepository).deleteById(100L);
    }

}