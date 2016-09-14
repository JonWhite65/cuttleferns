package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.State;

public interface SpringDataStateRepository extends JpaRepository<State, Long> {
	public List<State> findAllByName(String name);
}
