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
	@Autowired
	CommonService commonService;

	public City updateCity(long id, City b) {

		City a = repo.findOne(id);
		if (!a.getName().equals(b.getName())) {
			a.setName(b.getName());
		}
		if (!a.getState().equals(b.getState())) {
			a.setState(commonService.checkState(b.getState()));
		}
		return a;

	}

	public List<City> readAllCities() {

		return repo.findAll();
	}

	public Object readObjectByCity(long id, Object desired) {
		City requested = repo.findOne(id);
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

	public City createCity(City a) {
		City checkedCity = commonService.checkCity(a);
		return repo.saveAndFlush(checkedCity);

	}

	public City deleteCity(long id) {
		City a = repo.findOne(id);
		repo.delete(a);
		return a;
	}
}
