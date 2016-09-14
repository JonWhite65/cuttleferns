package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.City;
import com.cooksys.entity.Interest;

public interface SpringDataInterestRepository extends JpaRepository<Interest, Long> {
	 List<Interest> findAllByName(String name);


}
