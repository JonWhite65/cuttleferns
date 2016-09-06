package com.cooksys.repository.impl;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cooksys.repository.InterestRepository;
import com.cooksys.entity.*;

@Repository
public class InterestRepositoryImpl implements InterestRepository {

	@Autowired
	EntityManager em;

	@Override
	// returns the current interest based off id
	public Interest get(long id) {
		return em.createQuery("select interest from Interest interest where interest.id = :id", Interest.class)
				.setParameter("id", id).getResultList().get(0);

	}

	@Override
	// updates interest based off id
	public int updateInterest(long id, String name) {
		return em.createQuery("update interest from Interest interest set name=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", name).getResultList().get(0);

	}

	@Override
	public int updateInterest(long id, String property, Object value) {

		return em.createQuery("update interest from Interest interest set " + property + "=:name where id=:id",
				int.class).setParameter("id", id).setParameter("name", value).getResultList().get(0);
	}

	@Override
	public Interest deleteInterest(long id) {
		Interest saved = this.get(id);
		int value = em.createQuery("delete interest from Interest interest where id=:id", int.class)
				.setParameter("id", id).getResultList().get(0);
		if (value > 0) {
			return saved;
		} else {
			return null;
		}
	}

	@Override
	public Interest inputInterest(Interest a) {
		em.createNativeQuery("insert into interest (name) values(name)", Interest.class).setParameter("name",
				a.getName());
		return a;
	}

}