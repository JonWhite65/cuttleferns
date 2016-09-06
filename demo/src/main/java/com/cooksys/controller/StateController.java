package com.cooksys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.cooksys.entity.State;
import com.cooksys.repository.StateRepository;
import com.cooksys.service.StateService;

@RestController
@RequestMapping("states")
public class StateController {
	@Autowired
	private StateRepository stateRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<State> getStates() {
		return stateService.getAllStates();
	}

	@RequestMapping(method = RequestMethod.POST)
	public State inputState(@RequestBody State state) {
		return stateRepository.inputState(state);
	}

	@Autowired
	private StateService stateService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public State state(@PathVariable("id") long id) {
		return stateRepository.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public State updateState(@PathVariable("id") long id, @RequestBody State a) {
		return stateService.updateState(id, a.getName());

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public State deleteState(@PathVariable("id") long id) {
		return stateRepository.deleteState(id);
	}

}
