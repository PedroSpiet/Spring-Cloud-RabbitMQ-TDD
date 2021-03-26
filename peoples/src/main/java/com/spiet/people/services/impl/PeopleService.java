package com.spiet.people.services.impl;


import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.exceptions.ResourceNotFoundException;
import com.spiet.people.repositories.PeopleRepository;
import com.spiet.people.services.IPeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PeopleService implements IPeopleService {

    private PeopleRepository repo;

    public PeopleService(PeopleRepository repo) {
        this.repo = repo;
    }

    public People save(People people) {
        People createdPeople = repo.save(people);
        return createdPeople;
    }

    @Override
    public Page<PeopleDTO> findAll(Pageable pageable) {
        var page = repo.findAll(pageable);
        return page.map(this::convertToDTO);
    }

    @Override
    public People findById(Long id) {
        People people = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Uario nao encontrado"));
        return people;
    }

    private PeopleDTO convertToDTO(People people) {
        return new ModelMapper().map(people, PeopleDTO.class);
    }


}
