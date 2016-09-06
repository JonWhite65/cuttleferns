package com.cooksys.repository;

import com.cooksys.entity.City;
import com.cooksys.entity.State;

public interface CityRepository {

	City get(long id);

	City deleteCity(long id);

	City inputCity(City a);

	int updateCity(long id, String name, State state);

	int updateCity(long id, String name, Object value);

}