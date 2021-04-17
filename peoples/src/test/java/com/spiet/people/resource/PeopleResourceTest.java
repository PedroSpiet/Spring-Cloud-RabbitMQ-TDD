package com.spiet.people.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.services.IPeopleService;
import com.spiet.people.services.impl.PeopleService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PeopleResourceTest {

    static String BASE_URL = "/api/peoples";

    @MockBean
    IPeopleService service;

    @Autowired
    MockMvc mvc;

    public PeopleDTO createPeople() {
        return PeopleDTO.builder().id(1L).email("JonDoe@email.com").name("Jon Doe").tellphone(12345)
                .build();
    }

    @Test
    @DisplayName("Deve cadastrar uma pessoa")
    void shouldCreatePeople() throws Exception {
        String json = new ObjectMapper().writeValueAsString(createPeople());

        BDDMockito.given(service.save(Mockito.any(People.class))).willReturn(new ModelMapper().map(createPeople(), People.class));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(createPeople().getId()));
    }

    @Test
    @DisplayName("Deve buscar uma pessoa por id")
    void shouldReturnAPeople() throws Exception {
        PeopleDTO peopleDTO = createPeople();
        People convertPeople = new ModelMapper().map(peopleDTO, People.class);

        BDDMockito.given(service.findById(peopleDTO.getId())).willReturn(convertPeople);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(BASE_URL.concat("/" + convertPeople.getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(convertPeople.getId()));
    }
}
