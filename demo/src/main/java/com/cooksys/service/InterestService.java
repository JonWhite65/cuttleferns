package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.Interest;
import com.cooksys.repository.InterestRepository;
import com.cooksys.repository.SpringDataInterestRepository;

@Service
public class InterestService {
	@Autowired
	SpringDataInterestRepository repo;
	@Autowired
	private InterestRepository interestRepository;

	public Interest updateInterest(long id, String string) {

		Interest a = interestRepository.get(id);
		if (!a.getName().equals(string)) {
			interestRepository.updateInterest(id, "name", string);

		}
		return a;

	}

	public List<Interest> getAllInterests() {

		return repo.findAll();
	}

}
