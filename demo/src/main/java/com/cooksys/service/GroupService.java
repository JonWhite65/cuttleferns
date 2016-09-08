package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;
import com.cooksys.repository.SpringDataGroupRepository;

@Service
public class GroupService {
	@Autowired
	SpringDataGroupRepository repo;
	

	public Group updateGroup(long id, Group b) {

		Group a = repo.getOne(id);
		if(b.getName()!=null&&a.getName()!=null){
		if (!a.getName().equals(b.getName())) {
			a.setName(b.getName());
		}
		}
		
		if(b.getCity()!=null&&b.getCity().getName()!=null){
		if (!a.getCity().getName().equals(b.getCity().getName())) {
			a.setCity(b.getCity());
		}
		}
		
		if(b.getInterest()!=null&&b.getInterest().getName()!=null){
		if (!a.getInterest().getName().equals(b.getInterest().getName())) {
			a.setInterest(b.getInterest());
		}
		}
		
		if (!a.getMembers().containsAll(b.getMembers())) {
			// prevents duplicates from being added
			a.getMembers().removeAll(b.getMembers());
			a.getMembers().addAll(b.getMembers());
		}
		repo.saveAndFlush(a);
		return a;

	}
	public Group updateGroup(long id, String x, Object a){
		Group n = repo.getOne(id);
		if(x.equals("city")){
			n.setCity((City) a);
		}
		else if(x.equals("members")){
			n.setMembers((List<Person>)a);
		}
		else if(x.equals("interest")){
			n.setInterest((Interest)a);
		}
		else if(x.equals("name")){
			n.setName((String)a);
		}
		repo.saveAndFlush(n);
		return n;
	}
	

	public List<Group> readAllGroups() {

		return repo.findAll();
	}

	public Object readObjectByGroup(long id, Object desired) {
		Group requested = repo.getOne(id);
		if (desired.getClass().equals(City.class)) {
			return requested.getCity();
		} else if (desired.getClass().equals(String.class)) {
			if (desired.equals("interest")) {
				return requested.getInterest();
			}
			if (desired.equals("members")) {
				return requested.getMembers();
			}
		}

		return null;
	}

	public Object deleteListItemByGroup(long id, long id2, Object desired) {
		Group requested = repo.getOne(id);
		if (desired.equals("members")) {
			for (Person a : requested.getMembers()) {
				if (a.getId() == id2) {
					requested.getMembers().remove(a);
					repo.saveAndFlush(requested);
					return a;
				}
			}
		}

		return null;

	}
public Group createGroup(Group a){
		
		return repo.saveAndFlush(a);
	}
	public Group deleteGroup(long id){
		Group a=repo.getOne(id);
		repo.delete(a);
		return a;
	}
}
