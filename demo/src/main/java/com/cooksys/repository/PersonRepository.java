package com.cooksys.repository;

import java.util.List;

import com.cooksys.entity.Person;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;


public interface PersonRepository {

	Person get(long id);
	Person deletePerson(long id);
	Person inputPerson( Person a);
	int updatePerson(long id, String name, City city, List<Interest> interest, List<Group> group);
	int updatePerson(long id, String name, Object value);
}
