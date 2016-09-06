package com.cooksys.service;

import java.util.List;

import javax.persistence.EntityTransaction;

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

	public Person updatePerson(long id,Person b) {
		Person a = personRepository.get(id);
		if(b.getName()!=null&&a.getName()!=null){
		if (!a.getName().equals(b.getName())) {
			personRepository.updatePerson(id, "name", b.getName());
			
		}
		}
		if(b.getCity()!=null&&b.getCity().getName()!=null){
			
		if (!a.getCity().getName().equals(b.getCity().getName())) {
			personRepository.updatePerson(id, "city", b.getCity());

		}
		}
		if (!a.getInterest().containsAll(b.getInterest())) {
			// prevents duplicates from being added
			a.getInterest().removeAll(b.getInterest());
			a.getInterest().addAll(b.getInterest());
			personRepository.updatePerson(id, "interest", a.getInterest());

		}
		if (!a.getGroup().containsAll(b.getGroup())) {
			// prevents duplicates from being added
			a.getGroup().removeAll(b.getGroup());
			a.getGroup().addAll(b.getGroup());
			personRepository.updatePerson(id, "group", a.getGroup());
		}
		a = personRepository.get(id);

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
