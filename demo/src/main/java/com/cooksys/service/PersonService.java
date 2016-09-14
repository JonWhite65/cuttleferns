package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.*;
import com.cooksys.repository.SpringDataCityRepository;
import com.cooksys.repository.SpringDataGroupRepository;
import com.cooksys.repository.SpringDataInterestRepository;
import com.cooksys.repository.SpringDataPersonRepository;
import com.cooksys.repository.SpringDataStateRepository;

@Service
public class PersonService {
	@Autowired
	SpringDataPersonRepository repo;
	@Autowired
	SpringDataCityRepository cityRepo;
	@Autowired
	SpringDataInterestRepository interestRepo;
	@Autowired
	SpringDataGroupRepository groupRepo;
	@Autowired
	SpringDataStateRepository stateRepo;
	@Autowired
	CommonService commonService;

	public Person readPerson(long id) {
		Person a = repo.findOne(id);
		return a;
	}

	public Person updatePerson(long id, Person b) {
		
		Person a = repo.findOne(id);
		if (a != null && b != null) {
			
			if (b.getName() != null && (a.getName() == null || !a.getName().equals(b.getName()))) {
				a.setName(b.getName());
				repo.save(a);
			}
			if (b.getCity() != null && (a.getCity() == null || !a.getCity().equals(b.getCity()))) {
				a.setCity(commonService.checkCity(b.getCity()));
				cityRepo.save(a.getCity());
			
			}
			

		
		if (b.getInterest() != null) {
			List<Interest> c=new ArrayList<Interest>();
			
			for(Interest interest :(List<Interest>) b){
				c.add(commonService.checkInterest(interest));
			}
			
			if (!a.getInterest().containsAll(c)) {
				// prevents duplicates from being added
				a.getInterest().removeAll(c);
				a.getInterest().addAll(c);
				interestRepo.save(a.getInterest());
			}
		}

		if (b.getGroup() != null) {
			List<Group> c = new ArrayList<Group>();
			
			for(Group group :(List<Group>) b.getGroup()){
				c.add(commonService.checkGroup(group));
			}
			
			if (!a.getGroup().containsAll(c)) {
				// prevents duplicates from being added
				a.getGroup().removeAll(c);
				a.getGroup().addAll(c);
				groupRepo.save(a.getGroup());
			}
		}

		repo.save(a);
	}return a;

	}

	public Person updatePerson(long id, String x, Object a) {
		Person n = repo.findOne(id);
		if (a != null) {
			if (x.equals("city")) {
				n.setCity(commonService.checkCity((City) a));
				cityRepo.save((City) a);
			} else if (x.equals("group")) {
				List<Group> b = new ArrayList<Group>();
				
				for(Group group :(List<Group>) a){
					b.add(commonService.checkGroup(group));
				}
				n.setGroup(b);
				groupRepo.save(b);
			} else if (x.equals("interest")) {
				List<Interest> b=new ArrayList<Interest>();
				
				for(Interest interest :(List<Interest>) a){
					b.add(commonService.checkInterest(interest));
				}
				n.setInterest(b);
				interestRepo.save(b);
			} else if (x.equals("name")) {
				n.setName((String) a);
			}
		}
		repo.saveAndFlush(n);
		return n;
	}

	public List<Person> readAllPeople() {

		return repo.findAll();
	}

	public Object readObjectByPerson(long id, Object desired) {
		Person requested = repo.findOne(id);
		if (desired.getClass().equals(City.class)) {
			return requested.getCity();
		} else if (desired.getClass().equals(String.class)) {
			if (desired.equals("interest")) {
				return requested.getInterest();
			}
			if (desired.equals("group")) {
				return requested.getGroup();
			}
		}

		return null;
	}

	public Object deleteListItemByPerson(long id, long id2, Object desired) {

		Person requested = repo.findOne(id);
		if (desired.equals("interest")) {
			for (Interest a : requested.getInterest()) {
				if (a.getId() == id2) {
					requested.getInterest().remove(a);
					 repo.saveAndFlush(requested);
					return a;
				}
			}
		} else if (desired.equals("group")) {
			for (Group a : requested.getGroup()) {
				if (a.getId() == id2) {
					requested.getGroup().remove(a);
					 repo.saveAndFlush(requested);
					return a;
				}
			}
		}

		return null;

	}

	public Person createPerson(Person a) {
		if (a.getCity() != null) {
			cityRepo.save(commonService.checkCity(a.getCity()));
		}
		if (a.getInterest() != null) {
			List<Interest> b=new ArrayList<Interest>();
			
			for(Interest interest :(List<Interest>) a.getInterest()){
				b.add(commonService.checkInterest(interest));
			}
			interestRepo.save(b);
			a.setInterest(b);
		}
		if (a.getGroup() != null) {
			List<Group> b = new ArrayList<Group>();
			
			for(Group group :(List<Group>) a.getGroup()){
				b.add(commonService.checkGroup(group));
			}
			groupRepo.save(b);
			a.setGroup(b);
		}
		repo.save(a);
		return a;
	}

	public Person deletePerson(long id) {
		Person a = repo.findOne(id);
		repo.delete(a);
		return a;
	}
}
