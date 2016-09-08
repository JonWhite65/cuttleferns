package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.State;
import com.cooksys.repository.SpringDataStateRepository;

@Service
public class StateService {
	@Autowired
	SpringDataStateRepository repo;
	

	public State updateState(long id, String string) {

		State a = repo.getOne(id);
		if (!a.getName().equals(string)) {
			a.setName(string);
		}
		return a;

	}

	public List<State> readAllStates() {

		return repo.findAll();
	}
public State createState(State a){
		
		return repo.saveAndFlush(a);
	}
	public State deleteState(long id){
		State a=repo.getOne(id);
		repo.delete(a);
		return a;
	}

}
