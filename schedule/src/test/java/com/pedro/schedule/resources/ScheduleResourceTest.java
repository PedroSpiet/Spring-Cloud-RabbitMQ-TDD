package com.pedro.schedule.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.schedule.DTOs.ScheduleDTO;
import com.pedro.schedule.entities.Schedule;
import com.pedro.schedule.services.IScheduleService;
import com.pedro.schedule.services.impl.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WebMvcTest
public class ScheduleResourceTest {
    static String BASE_URL = "/api/schedule";

    @Autowired
    MockMvc mvc;

    @MockBean
    IScheduleService service;

    @Test
    @DisplayName("Deve Cadastrar uma pessoa na agenda")
    void shouldAddUser() throws Exception {
        ScheduleDTO dto = ScheduleDTO.builder().id(1L).email("Teste@email.com").name("Jon Doe").tellphone(12345)
                .build();

        Schedule schedule = Schedule.builder().id(dto.getId()).email(dto.getEmail()).name(dto.getName())
                .tellphone(dto.getTellphone()).build();

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        BDDMockito.given(service.create(Mockito.any(ScheduleDTO.class)))
                .willReturn(Schedule.builder().id(1L).email("JonDoe@email.com").name("Jon Doe")
                        .tellphone(12345).build());

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(schedule.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(schedule.getName()));
    }

    @Test
    @DisplayName("Deve exibir todos da agenda")
    void shouldReturnSchedules() throws Exception {
        ScheduleDTO dto = ScheduleDTO.builder().id(1L).email("Teste@email.com").name("Jon Doe").tellphone(12345)
                .build();

        Schedule schedule = Schedule.builder().id(dto.getId()).email(dto.getEmail()).name(dto.getName())
                .tellphone(dto.getTellphone()).build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON);

        List<Schedule> arr = Arrays.asList(schedule);

        BDDMockito.given(service.findAll()).willReturn(arr);
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

