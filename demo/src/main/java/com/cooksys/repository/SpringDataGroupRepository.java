package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.City;
import com.cooksys.entity.Group;

public interface SpringDataGroupRepository extends JpaRepository<Group, Long> {
	 List<Group> findAllByName(String name);


}
