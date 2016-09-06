package com.cooksys.repository;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.cooksys.entity.Person;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;


public interface PersonRepository {

	Person get(long id);
	Person deletePerson(long id);
	Person inputPerson( Person a);
	Person updatePerson(long id, String name, City city, List<Interest> interest, List<Group> group);
	void updatePerson(long id, String name, Object value);
}
