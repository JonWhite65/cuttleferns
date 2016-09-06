package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.State;
import com.cooksys.repository.StateRepository;
import com.cooksys.repository.SpringDataStateRepository;

@Service
public class StateService {
	@Autowired
	SpringDataStateRepository repo;
	@Autowired
	private StateRepository stateRepository;

	public State updateState(long id, String string) {

		State a = stateRepository.get(id);
		if (!a.getName().equals(string)) {
			stateRepository.updateState(id, "name", string);
		}
		return a;

	}

	public List<State> getAllStates() {

		return repo.findAll();
	}

}
