package com.cooksys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.Person;
import com.cooksys.repository.CityRepository;
import com.cooksys.repository.GroupRepository;
import com.cooksys.repository.SpringDataGroupRepository;

@Service
public class GroupService {
	@Autowired
	SpringDataGroupRepository repo;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private CityRepository cityRepository;

	public Group updateGroup(long id, String string, long l, Interest interest, List<Person> list2) {

		Group a = groupRepository.get(id);
		if (!a.getName().equals(string)) {
			groupRepository.updateGroup(id, "name", string);

		}
		if (a.getCity().getId() != l) {
			groupRepository.updateGroup(id, "city", cityRepository.get(l));

		}
		if (!a.getInterest().equals(interest)) {
			groupRepository.updateGroup(id, "interest", interest);

		}
		if (!a.getMembers().containsAll(list2)) {
			// prevents duplicates from being added
			a.getMembers().removeAll(list2);
			a.getMembers().addAll(list2);
			groupRepository.updateGroup(id, "group", a.getMembers());

		}
		return a;

	}

	public List<Group> getAllGroups() {

		return repo.findAll();
	}

	public Object getObjectByGroup(long id, Object desired) {
		Group requested = groupRepository.get(id);
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
		Group requested = groupRepository.get(id);
		if (desired.equals("members")) {
			for (Person a : requested.getMembers()) {
				if (a.getId() == id2) {
					requested.getMembers().remove(a);
					groupRepository.updateGroup(id, "group", requested.getMembers());
					return a;
				}
			}
		}

		return null;

	}
}
