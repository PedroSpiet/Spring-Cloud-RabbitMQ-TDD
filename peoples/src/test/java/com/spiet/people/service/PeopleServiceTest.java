package com.spiet.people.service;

import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.messages.PeopleSendMessage;
import com.spiet.people.repositories.PeopleRepository;
import com.spiet.people.services.IPeopleService;
import com.spiet.people.services.impl.PeopleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PeopleServiceTest {

    IPeopleService service;

    @MockBean
    PeopleRepository repository;

    PeopleSendMessage psm;

    @BeforeEach
    public void setUp() {
        this.service = new PeopleService(repository, psm);
    }

    @Test
    @DisplayName("Deve Cadastrar uma pessoa na base de dados")
    void shouldCreateAPeople() {
        People people = People.builder().id(1L).name("Jon Doe").email("Jon@email.com").tellphone(12345)
                .build();

        Mockito.when( service.save(Mockito.any(People.class)) ).thenReturn(
                People.builder().id(people.getId()).name(people.getName()).email(people.getEmail())
                .tellphone(people.getTellphone()).build());

        People savedPeople = service.save(people);

        Assertions.assertThat(savedPeople.getId()).isNotNull().isEqualTo(1L);
        Assertions.assertThat(savedPeople.getName()).isNotNull().isEqualTo("Jon Doe");
        Assertions.assertThat(savedPeople.getEmail()).isNotNull().isEqualTo("Jon@email.com");
        Assertions.assertThat(savedPeople.getTellphone()).isNotNull().isEqualTo(12345);
    }
}
