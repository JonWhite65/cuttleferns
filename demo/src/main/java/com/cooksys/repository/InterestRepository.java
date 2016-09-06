package com.cooksys.repository;

import com.cooksys.entity.Interest;

public interface InterestRepository {

	Interest get(long id);

	Interest deleteInterest(long id);

	Interest inputInterest(Interest a);

	int updateInterest(long id, String name);

	int updateInterest(long id, String name, Object value);

}