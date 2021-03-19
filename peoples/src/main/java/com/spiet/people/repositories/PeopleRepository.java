package com.spiet.people.repositories;

import com.spiet.people.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    boolean existsByEmail(String email);
}
