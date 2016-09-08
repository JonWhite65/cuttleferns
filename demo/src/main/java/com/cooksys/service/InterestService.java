package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;
import com.cooksys.repository.SpringDataInterestRepository;

@Service
public class InterestService {
	@Autowired
	SpringDataInterestRepository repo;

	public Interest updateInterest(long id, String string) {

		Interest a = repo.getOne(id);
		if (!a.getName().equals(string)) {
			a.setName(string);

		}
		repo.saveAndFlush(a);
		return a;

	}

	public List<Interest> readAllInterests() {

		return repo.findAll();
	}
public Interest createInterest(Interest a){
		
		return repo.saveAndFlush(a);
	}
public Interest deleteInterest(long id){
	Interest a=repo.getOne(id);
	repo.delete(a);
	return a;
}
}
