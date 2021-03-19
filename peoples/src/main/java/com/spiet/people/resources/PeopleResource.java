package com.spiet.people.resources;

import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.events.ResourceCreatedEvent;
import com.spiet.people.services.IPeopleService;
import com.spiet.people.services.impl.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/peoples")
public class PeopleResource {


    IPeopleService service;

    ApplicationEventPublisher publisher;

    ModelMapper modelMapper;

    @Autowired
    public PeopleResource(IPeopleService service, ApplicationEventPublisher publisher, ModelMapper modelMapper) {
        this.service = service;
        this.publisher = publisher;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PeopleDTO> create(@RequestBody PeopleDTO dto, HttpServletResponse response) {
        People people = service.save(modelMapper.map(dto, People.class));
        publisher.publishEvent(new ResourceCreatedEvent(this, response, people.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(people, PeopleDTO.class));
    }
}
