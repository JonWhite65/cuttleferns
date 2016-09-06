package com.cooksys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.cooksys.entity.City;
import com.cooksys.entity.State;
import com.cooksys.repository.CityRepository;
import com.cooksys.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<City> getPeople() {
		return cityService.getAllCities();
	}

	@RequestMapping(method = RequestMethod.POST)
	public City inputCity(@RequestBody City city) {
		return cityRepository.inputCity(city);
	}

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City city(@PathVariable("id") long id) {
		return cityRepository.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public City updateCity(@PathVariable("id") long id, @RequestBody City a) {
		return cityService.updateCity(id, a.getName(), a.getState().getId());

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public City deleteCity(@PathVariable("id") long id) {
		return cityRepository.deleteCity(id);
	}

	@RequestMapping(value = "/{id}/state", method = RequestMethod.GET)
	public City getStateByCity(@PathVariable("id") long id) {
		return (City) cityService.getObjectByCity(id, new State());
	}

	@RequestMapping(value = "/{id}/state", method = RequestMethod.PUT)
	public City inputStateByCity(@PathVariable("id") long id, @RequestBody State state) {
		cityRepository.updateCity(id, "state", state);
		return cityRepository.get(id);
	}

}
