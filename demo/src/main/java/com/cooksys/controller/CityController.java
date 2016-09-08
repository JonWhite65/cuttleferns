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
import com.cooksys.repository.SpringDataCityRepository;
import com.cooksys.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private SpringDataCityRepository sdcr;

	@RequestMapping(method = RequestMethod.GET)
	public List<City> getPeople() {
		return cityService.readAllCities();
	}

	@RequestMapping(method = RequestMethod.POST)
	public City inputCity(@RequestBody City city) {
		return cityService.createCity(city);
	}

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City city(@PathVariable("id") long id) {
		return sdcr.getOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public City updateCity(@PathVariable("id") long id, @RequestBody City a) {
		return cityService.updateCity(id, a);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public City deleteCity(@PathVariable("id") long id) {
		return cityService.deleteCity(id);
	}

	@RequestMapping(value = "/{id}/state", method = RequestMethod.GET)
	public City getStateByCity(@PathVariable("id") long id) {
		return (City) cityService.readObjectByCity(id, new State());
	}

	@RequestMapping(value = "/{id}/state", method = RequestMethod.PUT)
	public City inputStateByCity(@PathVariable("id") long id, @RequestBody State state) {
		return cityService.updateCity(id,new City(sdcr.getOne(id).getName(),state));
		
	}

}
