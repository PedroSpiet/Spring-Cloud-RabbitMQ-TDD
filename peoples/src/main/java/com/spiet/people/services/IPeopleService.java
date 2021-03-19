package com.spiet.people.services;

import com.spiet.people.DTOs.PeopleDTO;
import com.spiet.people.entities.People;

public interface IPeopleService {
    People save(People people);
}
