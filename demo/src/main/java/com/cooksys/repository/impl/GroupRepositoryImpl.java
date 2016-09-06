package com.cooksys.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cooksys.repository.GroupRepository;
import com.cooksys.entity.*;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

	@Autowired
	EntityManager em;

	@Override
	// returns the current group based off id
	public Group get(long id) {
		return em.createQuery("select group from Group group where group.id = :id", Group.class).setParameter("id", id)
				.getResultList().get(0);

	}

	@Override
	// updates group based off id
	public int updateGroup(long id, String name, City city, Interest interest, List<Person> members) {
		return em
				.createQuery(
						"update group from Group group set name=:name,city=:city,interest=:interest,members=:members where id=:id",
						int.class)
				.setParameter("id", id).setParameter("name", name).setParameter("city", city)
				.setParameter("interest", interest).setParameter("members", members).getResultList().get(0);

	}

	@Override
	public int updateGroup(long id, String property, Object value) {

		return em.createQuery("update group from Group group set " + property + "=:name where id=:id", int.class)
				.setParameter("id", id).setParameter("name", value).getResultList().get(0);
	}

	@Override
	public Group deleteGroup(long id) {
		Group saved = this.get(id);
		int value = em.createQuery("delete group from Group group where id=:id", int.class).setParameter("id", id)
				.getResultList().get(0);
		if (value > 0) {
			return saved;
		} else {
			return null;
		}
	}

	@Override
	public Group inputGroup(Group a) {
		em.createNativeQuery("insert into group (name,city,intrest,members) values(name,city,intrest,members)",
				Group.class).setParameter("name", a.getName()).setParameter("city", a.getCity())
				.setParameter("intrest", a.getInterest()).setParameter("group", a.getMembers());
		return a;
	}

}