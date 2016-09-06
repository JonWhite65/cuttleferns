package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Interest;

public interface SpringDataInterestRepository extends JpaRepository<Interest, Long> {
	


}
