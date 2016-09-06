package com.cooksys.repository.impl;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cooksys.repository.CityRepository;
import com.cooksys.entity.*;

@Repository
public class CityRepositoryImpl implements CityRepository {

	@Autowired
	EntityManager em;

	@Override
	// returns the current city based off id
	public City get(long id) {
		return em.createQuery("select city from City city where city.id = :id", City.class).setParameter("id", id)
				.getResultList().get(0);

	}

	@Override
	// updates city based off id
	public int updateCity(long id, String name, State state) {
		return em.createQuery("update city from City city set name=:name,state=:state where id=:id", int.class)
				.setParameter("id", id).setParameter("name", name).setParameter("state", state).getResultList().get(0);

	}

	@Override
	public int updateCity(long id, String property, Object value) {

		return em.createQuery("update city from City city set " + property + "=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", value).getResultList().get(0);
	}

	@Override
	public City deleteCity(long id) {
		City saved = this.get(id);
		int value = em.createQuery("delete city from City city where id=:id", int.class).setParameter("id", id)
				.getResultList().get(0);
		if (value > 0) {
			return saved;
		} else {
			return null;
		}
	}

	@Override
	public City inputCity(City a) {
		 em.createNativeQuery("insert into city (name,state) values(:name,:state)", City.class)
				.setParameter("name", a.getName()).setParameter("state", a.getState())
				.executeUpdate();
		return a;
	}
}