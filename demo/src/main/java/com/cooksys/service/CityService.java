package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.City;
import com.cooksys.entity.State;
import com.cooksys.repository.CityRepository;
import com.cooksys.repository.SpringDataCityRepository;

@Service
public class CityService {

	@Autowired
	SpringDataCityRepository repo;
	@Autowired
	private CityRepository cityRepository;

	public City updateCity(long id, String string, long l) {

		City a = cityRepository.get(id);
		if (!a.getName().equals(string)) {
			cityRepository.updateCity(id, "name", string);

		}
		if (a.getState().getId() != l) {
			cityRepository.updateCity(id, "state", cityRepository.get(l));

		}
		return a;

	}

	public List<City> getAllCities() {

		return repo.findAll();
	}

	public Object getObjectByCity(long id, Object desired) {
		City requested = cityRepository.get(id);
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

}
