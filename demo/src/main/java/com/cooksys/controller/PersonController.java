package com.cooksys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;
import com.cooksys.repository.SpringDataPersonRepository;
import com.cooksys.service.PersonService;
//read create delete index
@RestController
@RequestMapping("people")
public class PersonController {
	@Autowired
	private SpringDataPersonRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Person> getPeople() {
		return personService.readAllPeople();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Person inputPerson(@RequestBody Person person) {
		return personService.createPerson(person);
	}

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person person(@PathVariable("id") long id) {
		return personService.readPerson(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public Person updatePerson(@PathVariable("id") long id, @RequestBody Person a) {
		return personService.updatePerson(id,a);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Person deletePerson(@PathVariable("id") long id) {
		return personService.deletePerson(id);
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
	public City getCityByPerson(@PathVariable("id") long id) {
		return (City) personService.readObjectByPerson(id, new City());
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.PUT)
	public Person inputCityByPerson(@PathVariable("id") long id, @RequestBody City city) {
		return personService.updatePerson(id, "city", city);
		
	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.GET)
	public List<Interest> getAllInterestByPerson(@PathVariable("id") long id) {
		return (List<Interest>) personService.readObjectByPerson(id, "interest");
	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.PUT)
	public Person inputInterestByPerson(@PathVariable("id") long id, @RequestBody List<Interest> interest) {
		return personService.updatePerson(id, "interest", interest);
		
	}

	@RequestMapping(value = "/{id}/interests/{id2}", method = RequestMethod.DELETE)
	public Interest deleteInterestByPerson(@PathVariable("id") long id, @PathVariable("id2") long id2) {
		return (Interest) personService.deleteListItemByPerson(id, id2, "interest");
	}

	@RequestMapping(value = "/{id}/groups", method = RequestMethod.GET)
	public List<Group> getAllGroupsByPerson(@PathVariable("id") long id) {
		return (List<Group>) personService.readObjectByPerson(id, "group");
	}

	@RequestMapping(value = "/{id}/groups", method = RequestMethod.PUT)
	public Person inputGroupByPerson(@PathVariable("id") long id, @RequestBody List<Group> group) {
		return personService.updatePerson(id, "group", group);
		
	}

	@RequestMapping(value = "/{id}/groups/{id2}", method = RequestMethod.DELETE)
	public Group deleteGroupByPerson(@PathVariable("id") long id, @PathVariable("id2") long id2) {
		return (Group) personService.deleteListItemByPerson(id, id2, "group");
	}
}
