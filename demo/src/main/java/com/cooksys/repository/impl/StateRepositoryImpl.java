package com.cooksys.repository.impl;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cooksys.repository.StateRepository;
import com.cooksys.entity.*;

@Repository
public class StateRepositoryImpl implements StateRepository {

	@Autowired
	EntityManager em;

	@Override
	// returns the current state based off id
	public State get(long id) {
		return em.createQuery("select state from State state where state.id = :id", State.class).setParameter("id", id)
				.getResultList().get(0);

	}

	@Override
	// updates state based off id
	public int updateState(long id, String name) {
		return em.createQuery("update state from State state set name=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", name).getResultList().get(0);

	}

	@Override
	public int updateState(long id, String property, Object value) {

		return em.createQuery("update state from State state set " + property + "=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", value).getResultList().get(0);
	}

	@Override
	public State deleteState(long id) {
		State saved = this.get(id);
		int value = em.createQuery("delete state from State state where id=:id", int.class).setParameter("id", id)
				.getResultList().get(0);
		if (value > 0) {
			return saved;
		} else {
			return null;
		}
	}

	@Override
	public State inputState(State a) {
		em.createNativeQuery("insert into state (name) values(:name)", State.class).setParameter("name", a.getName());
		return a;
	}

}
