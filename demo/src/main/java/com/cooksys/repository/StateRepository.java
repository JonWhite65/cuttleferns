package com.cooksys.repository;

import com.cooksys.entity.State;

public interface StateRepository {

	State get(long id);

	State deleteState(long id);

	State inputState(State a);

	int updateState(long id, String name);

	int updateState(long id, String name, Object value);

}