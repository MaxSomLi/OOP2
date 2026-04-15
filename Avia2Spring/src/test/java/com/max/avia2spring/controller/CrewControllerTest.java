package com.max.avia2spring.controller;

import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.repository.CrewMemberRepository;
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
public class CrewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CrewMemberRepository crewRepository;

    @Test
    @WithMockUser(roles = "2")
    void testAddCrewMember_ShouldHashPassword() throws Exception {
        mockMvc.perform(post("/assign/addCrew")
                        .param("name", "Ivan")
                        .param("password", "rawPassword123")
                        .param("admin", "false")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));
        verify(crewRepository).save(any(CrewMember.class));
    }

    @Test
    @WithMockUser(roles = "2")
    void testDeleteCrewMember() throws Exception {
        mockMvc.perform(post("/assign/deleteCrew")
                        .param("id", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/assign"));

        verify(crewRepository).deleteById(1L);
    }
}