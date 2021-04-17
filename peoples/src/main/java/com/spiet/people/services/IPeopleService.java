package com.spiet.people.services;

import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IPeopleService {
    People save(People people);

    Page<PeopleDTO> findAll(Pageable pageable);

    People findById(Long id);
}
