package com.spiet.people.services.impl;


import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import com.spiet.people.repositories.PeopleRepository;
import com.spiet.people.services.IPeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PeopleService implements IPeopleService {

    private PeopleRepository repo;

    public PeopleService(PeopleRepository repo) {
        this.repo = repo;
    }

    public People save(PeopleDTO people) {
        People createdPeople = repo.save(new ModelMapper().map(people, People.class));
        return createdPeople;
    }
}
