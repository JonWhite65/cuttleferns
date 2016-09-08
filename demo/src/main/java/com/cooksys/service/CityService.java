package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.City;
import com.cooksys.entity.Person;
import com.cooksys.entity.State;
import com.cooksys.repository.SpringDataCityRepository;

@Service
public class CityService {

	@Autowired
	SpringDataCityRepository repo;

	public  City updateCity(long id, City b) {

		City a = repo.getOne(id);
		if (!a.getName().equals(b.getName())) {
			a.setName(b.getName());
		}
		if (!a.getState().getName().equals(b.getState().getName())) {
			a.setState(b.getState());
		}
		return a;

	}
	

	public List<City> readAllCities() {

		return repo.findAll();
	}

	public Object readObjectByCity(long id, Object desired) {
		City requested = repo.getOne(id);
		if (desired.getClass().equals(State.class)) {
			return requested.getState();
		} else if (desired.getClass().equals(String.class)) {
			if (desired.equals("interest")) {
				// return requested.getInterest();
			}
			if (desired.equals("group")) {
				// return requested.getGroup();
			}
		}

		return null;
	}
public City createCity(City a){
		
		return repo.saveAndFlush(a);
	}
	public City deleteCity(long id){
		City a=repo.getOne(id);
		repo.delete(a);
		return a;
	}
}
