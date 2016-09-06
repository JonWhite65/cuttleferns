package com.cooksys.repository;

import java.util.List;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;


public interface GroupRepository {

	Group get(long id);
	Group deleteGroup(long id);
	Group inputGroup( Group a);
	int updateGroup(long id, String name, City city, Interest interest, List<Person> members);
	int updateGroup(long id, String name, Object value);
	
}