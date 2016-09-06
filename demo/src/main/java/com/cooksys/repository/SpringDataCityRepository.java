package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.City;

public interface SpringDataCityRepository extends JpaRepository<City, Long> {


}
