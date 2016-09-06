package com.cooksys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.cooksys.entity.Interest;
import com.cooksys.repository.InterestRepository;
import com.cooksys.service.InterestService;

@RestController
@RequestMapping("interests")
public class InterestController {

	@Autowired
	private InterestRepository interestRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Interest> getPeople() {

		return interestService.getAllInterests();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Interest inputInterest(@RequestBody Interest interest) {
		return interestRepository.inputInterest(interest);
	}

	@Autowired
	private InterestService interestService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Interest interest(@PathVariable("id") long id) {
		return interestRepository.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public Interest updateInterest(@PathVariable("id") long id, @RequestBody Interest a) {
		return interestService.updateInterest(id, a.getName());

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Interest deleteInterest(@PathVariable("id") long id) {
		return interestRepository.deleteInterest(id);
	}

}
