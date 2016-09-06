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
import com.cooksys.repository.PersonRepository;
import com.cooksys.service.PersonService;

@RestController
@RequestMapping("people")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Person> getPeople() {
		return personService.getAllPeople();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Person inputPerson(@RequestBody Person person) {
		return personRepository.inputPerson(person);
	}

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person person(@PathVariable("id") long id) {
		return personRepository.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public Person updatePerson(@PathVariable("id") long id, @RequestBody Person a) {
		return personService.updatePerson(id, a.getName(), a.getCity().getId(), a.getInterest(), a.getGroup());

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Person deletePerson(@PathVariable("id") long id) {
		return personRepository.deletePerson(id);
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
	public City getCityByPerson(@PathVariable("id") long id) {
		return (City) personService.getObjectByPerson(id, new City());
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.PUT)
	public Person inputCityByPerson(@PathVariable("id") long id, @RequestBody City city) {
		personRepository.updatePerson(id, "city", city);
		return personRepository.get(id);
	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.GET)
	public List<?> getAllInterestByPerson(@PathVariable("id") long id) {
		return (List<?>) personService.getObjectByPerson(id, "interest");
	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.PUT)
	public Person inputInterestByPerson(@PathVariable("id") long id, @RequestBody List<Interest> interest) {
		personRepository.updatePerson(id, "interest", interest);
		return personRepository.get(id);
	}

	@RequestMapping(value = "/{id}/interests/{id2}", method = RequestMethod.DELETE)
	public Interest deleteInterestByPerson(@PathVariable("id") long id, @PathVariable("id2") long id2) {
		return (Interest) personService.deleteListItemByPerson(id, id2, "interest");
	}

	@RequestMapping(value = "/{id}/groups", method = RequestMethod.GET)
	public List<?> getAllGroupsByPerson(@PathVariable("id") long id) {
		return (List<?>) personService.getObjectByPerson(id, "group");
	}

	@RequestMapping(value = "/{id}/groups", method = RequestMethod.PUT)
	public Person inputGroupByPerson(@PathVariable("id") long id, @RequestBody List<?> group) {
		personRepository.updatePerson(id, "group", group);
		return personRepository.get(id);
	}

	@RequestMapping(value = "/{id}/groups/{id2}", method = RequestMethod.DELETE)
	public Group deleteGroupByPerson(@PathVariable("id") long id, @PathVariable("id2") long id2) {
		return (Group) personService.deleteListItemByPerson(id, id2, "group");
	}
}
