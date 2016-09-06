package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.State;

public interface SpringDataStateRepository extends JpaRepository<State, Long> {

}
