package com.cooksys.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cooksys.entity.*;
import com.cooksys.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Autowired
	EntityManager em;

	@Override
	// returns the current person based off id
	public Person get(long id) {
		return em.createQuery("select person from Person person where person.id = :id", Person.class)
				.setParameter("id", id).getResultList().get(0);

	}

	@Override
	// updates person based off id
	public int updatePerson(long id, String name, City city, List<Interest> interest, List<Group> group) {
		return em
				.createQuery(
						"update person from Person person set name=:name,city=:city,interest=:interest,group=:group where id=:id",
						int.class)
				.setParameter("id", id).setParameter("name", name).setParameter("city", city)
				.setParameter("interest", interest).setParameter("group", group).getResultList().get(0);

	}

	@Override
	public int updatePerson(long id, String property, Object value) {

		return em.createQuery("update person from Person person set " + property + "=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", value).getResultList().get(0);
	}

	@Override
	public Person deletePerson(long id) {
		Person saved = this.get(id);
		int value = em.createQuery("delete person from Person person where id=:id", int.class).setParameter("id", id)
				.getResultList().get(0);
		if (value > 0) {
			return saved;
		} else {
			return null;
		}
	}

	@Override
	public Person inputPerson(Person a) {
		em.createNativeQuery("insert into person (name,city,intrest,group) values(name,city,intrest,group)",
				Person.class).setParameter("name", a.getName()).setParameter("city", a.getCity())
				.setParameter("intrest", a.getInterest()).setParameter("group", a.getGroup());
		return a;
	}

}