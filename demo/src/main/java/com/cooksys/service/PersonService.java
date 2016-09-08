package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.*;
import com.cooksys.repository.SpringDataCityRepository;
import com.cooksys.repository.SpringDataGroupRepository;
import com.cooksys.repository.SpringDataInterestRepository;
import com.cooksys.repository.SpringDataPersonRepository;
import com.cooksys.service.CityService;
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

	public Person readPerson(long id){
		Person a =repo.findOne(id);
		return a;
	}
	public Person updatePerson(long id,Person b) {
		Person a = repo.findOne(id);
		
		if(b.getName()!=null&&a.getName()!=null){
		if (!a.getName().equals(b.getName())) {
			a.setName(b.getName());
			repo.save(a);
		}
		}
		
		if(b.getCity()!=null){
			a.setCity(b.getCity());
			CityService cs =new CityService();
			List<City>cityList=cs.readAllCities();
			for(City each :cityList){
				if(a.getCity().equals(each)){
					a.setId(each.getId());
					break;
				}
			}
			cityRepo.save(a.getCity());
			
		}
		
		if(b.getInterest()!=null){
		if (!a.getInterest().containsAll(b.getInterest())) {
			// prevents duplicates from being added
			a.getInterest().removeAll(b.getInterest());
			a.getInterest().addAll(b.getInterest());
			interestRepo.save(a.getInterest());
		}
		}
		
		if(b.getGroup()!=null){
		if (!a.getGroup().containsAll(b.getGroup())) {
			// prevents duplicates from being added
			a.getGroup().removeAll(b.getGroup());
			a.getGroup().addAll(b.getGroup());
			groupRepo.save(a.getGroup());
		}
		}
		
		
		repo.saveAndFlush(a);
		return a;

	}
	public Person updatePerson(long id, String x, Object a){
		Person n = repo.findOne(id);
		if(a!=null){
		if(x.equals("city")){
			n.setCity((City) a);
			cityRepo.save((City)a);
		}
		else if(x.equals("group")){
			n.setGroup((List<Group>)a);
			groupRepo.save(n.getGroup());
		}
		else if(x.equals("interest")){
			n.setInterest((List<Interest>)a);
			interestRepo.save(n.getInterest());
		}
		else if(x.equals("name")){
			n.setName((String)a);
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
					//repo.saveAndFlush(requested);
					return a;
				}
			}
		} else if (desired.equals("group")) {
			for (Group a : requested.getGroup()) {
				if (a.getId() == id2) {
					requested.getGroup().remove(a);
					//repo.saveAndFlush(requested);
					return a;
				}
			}
		}

		return null;

	}
	public Person createPerson(Person a){
		if(a.getCity()!=null){
			cityRepo.save(a.getCity());
		}
		if(a.getInterest()!=null){
			interestRepo.save(a.getInterest());
		}
		if(a.getGroup()!=null){
			groupRepo.save(a.getGroup());
		}
		repo.save(a);
		return a;
	}
	public Person deletePerson(long id){
		Person a=repo.findOne(id);
		repo.delete(a);
		return a;
	}
}
