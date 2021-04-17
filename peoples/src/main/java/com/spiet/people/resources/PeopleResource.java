package com.spiet.people.resources;

import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.events.ResourceCreatedEvent;
import com.spiet.people.services.IPeopleService;
import com.spiet.people.services.impl.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/peoples")
public class PeopleResource {


    IPeopleService service;

    ApplicationEventPublisher publisher;

    ModelMapper modelMapper;

    PagedResourcesAssembler<PeopleDTO> assembler;

    @Autowired
    public PeopleResource(IPeopleService service, ApplicationEventPublisher publisher, ModelMapper modelMapper,
                          PagedResourcesAssembler<PeopleDTO> assembler) {
        this.service = service;
        this.publisher = publisher;
        this.modelMapper = modelMapper;
        this.assembler = assembler;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PeopleDTO> create(@RequestBody PeopleDTO dto, HttpServletResponse response) {
        People people = service.save(modelMapper.map(dto, People.class));
        publisher.publishEvent(new ResourceCreatedEvent(this, response, people.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(people, PeopleDTO.class));
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

        Page<PeopleDTO> peoples = service.findAll(pageable);

        peoples.stream().forEach(p ->
                p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PeopleResource.class)
                        .findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<PeopleDTO>> pagedModel = assembler.toModel(peoples);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PeopleDTO> findById(@PathVariable Long id) {
        PeopleDTO people = modelMapper.map(service.findById(id), PeopleDTO.class);
        return ResponseEntity.ok().body(people);

    }
}
