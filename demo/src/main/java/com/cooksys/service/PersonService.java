package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.*;
import com.cooksys.repository.CityRepository;
import com.cooksys.repository.PersonRepository;
import com.cooksys.repository.SpringDataPersonRepository;

@Service
public class PersonService {
	@Autowired
	SpringDataPersonRepository repo;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private CityRepository cityRepository;

	public Person updatePerson(long id, String string, long l, List<Interest> list, List<Group> list2) {

		Person a = personRepository.get(id);
		if (!a.getName().equals(string)) {
			personRepository.updatePerson(id, "name", string);

		}
		if (a.getCity().getId() != l) {
			personRepository.updatePerson(id, "city", cityRepository.get(l));

		}
		if (!a.getInterest().containsAll(list)) {
			// prevents duplicates from being added
			a.getInterest().removeAll(list);
			a.getInterest().addAll(list);
			personRepository.updatePerson(id, "interest", a.getInterest());

		}
		if (!a.getGroup().containsAll(list2)) {
			// prevents duplicates from being added
			a.getGroup().removeAll(list2);
			a.getGroup().addAll(list2);
			personRepository.updatePerson(id, "group", a.getGroup());
		}
		return a;

	}

	public List<Person> getAllPeople() {

		return repo.findAll();
	}

	public Object getObjectByPerson(long id, Object desired) {
		Person requested = personRepository.get(id);
		if (desired.getClass().equals(City.class)) {
			return requested.getCity();
		} else if (desired.getClass().equals(String.class)) {
			if (desired.equals("interest")) {
				return requested.getInterest();
			}
			if (desired.equals("group")) {
				return requested.getGroup();
			}
		}

		return null;
	}

	public Object deleteListItemByPerson(long id, long id2, Object desired) {

		Person requested = personRepository.get(id);
		if (desired.equals("interest")) {
			for (Interest a : requested.getInterest()) {
				if (a.getId() == id2) {
					requested.getInterest().remove(a);
					personRepository.updatePerson(id, "interest", requested.getInterest());
					return a;
				}
			}
		} else if (desired.equals("group")) {
			for (Group a : requested.getGroup()) {
				if (a.getId() == id2) {
					requested.getGroup().remove(a);
					personRepository.updatePerson(id, "group", requested.getGroup());
					return a;
				}
			}
		}

		return null;

	}
}
