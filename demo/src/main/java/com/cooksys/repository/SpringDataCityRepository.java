package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.City;

public interface SpringDataCityRepository extends JpaRepository<City, Long> {
	 List<City> findAllByName(String name);
	 
}
