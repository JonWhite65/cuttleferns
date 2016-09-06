package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Person;

public interface SpringDataPersonRepository extends JpaRepository<Person, Long> {

}
