package com.cooksys.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Commit;

import com.cooksys.entity.*;
import com.cooksys.repository.PersonRepository;

@Repository
@Transactional
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
	public Person updatePerson(long id, String name, City city, List<Interest> interest, List<Group> group) {
		em.joinTransaction();
		 em.createQuery("update Person person set name=:name,city=:city,interest=:interest,group=:group where id=:id")
				.setParameter("id", id).setParameter("name", name).setParameter("city", city)
				.setParameter("interest", interest).setParameter("group", group)
				.executeUpdate();
		 return this.get(id);
	}

	@Override
	@Transactional
	public void updatePerson(long id, String property, Object value) {
		em.joinTransaction();
		System.out.println("here");
		 em.createQuery("update Person  set " + property + "=:name where id=:id")
				.setParameter("id", id).setParameter("name", value)
				.executeUpdate();
		 em.flush();
	
		 
	}

	@Override
	@Transactional
	public Person deletePerson(long id) {
		Person saved = this.get(id);
		em.joinTransaction();
			em.createQuery("delete Person where id=:id")
			.setParameter("id", id)
			.executeUpdate();
		  return saved;
		
	}

	@Override
	public Person inputPerson(Person a) {
		em.joinTransaction();
		em.createNativeQuery("insert into Person (name,city,intrest,group) values(:name,:city,:intrest,:group)").setParameter("name", a.getName()).setParameter("city", a.getCity())
				.setParameter("intrest", a.getInterest()).setParameter("group", a.getGroup()).executeUpdate();
		return a;
	}

}