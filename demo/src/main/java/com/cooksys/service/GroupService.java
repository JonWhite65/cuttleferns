package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;
import com.cooksys.repository.SpringDataCityRepository;
import com.cooksys.repository.SpringDataGroupRepository;
import com.cooksys.repository.SpringDataInterestRepository;

@Service
public class GroupService {
	@Autowired
	SpringDataGroupRepository repo;
	@Autowired
	CommonService commonService;
	@Autowired
	SpringDataCityRepository cityRepo;
	@Autowired
	SpringDataInterestRepository interestRepo;
	

	public Group updateGroup(long id, Group b) {
		Group a = repo.findOne(id);
		if (a != null && b != null) {
		
		if(b.getName() != null && (a.getName() == null || !a.getName().equals(b.getName()))){
		if (!a.getName().equals(b.getName())) {
			a.setName(b.getName());
			repo.save(a);
		}
		}
		
		if(b.getCity()!=null&&(a.getCity() == null || !a.getCity().equals(b.getCity()))){
		
			a.setCity(commonService.checkCity(b.getCity()));
			cityRepo.save(a.getCity());
		}
		
		if(b.getInterest()!=null&&b.getInterest().getName()!=null){
		if (!a.getInterest().getName().equals(b.getInterest().getName())) {
			
			a.setInterest(commonService.checkInterest(b.getInterest()));
			interestRepo.save(a.getInterest());
		}
		}
		
		if (!a.getMembers().containsAll(b.getMembers())) {
			// prevents duplicates from being added
			a.getMembers().removeAll(b.getMembers());
			a.getMembers().addAll(b.getMembers());
		}
		repo.saveAndFlush(a);
		
		}
		return a;
	}
		
	public Group updateGroup(long id, String x, Object a){
		Group n = repo.findOne(id);
		if(x.equals("city")){
			n.setCity(commonService.checkCity((City) a));
			cityRepo.save((City) a);
		}
		else if(x.equals("members")){
			n.setMembers((List<Person>)a);
		}
		else if(x.equals("interest")){
			n.setInterest(commonService.checkInterest((Interest) a));
			interestRepo.save((Interest) a);
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
		Group requested = repo.findOne(id);
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
		Group requested = repo.findOne(id);
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
	Group checkedGroup = commonService.checkGroup(a);
	return repo.saveAndFlush(checkedGroup);
	}
	public Group deleteGroup(long id){
		Group a=repo.getOne(id);
		repo.delete(a);
		return a;
	}
}
