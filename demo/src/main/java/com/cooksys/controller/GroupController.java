package com.cooksys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.repository.SpringDataGroupRepository;
import com.cooksys.service.GroupService;

@RestController
@RequestMapping("groups")
public class GroupController {

	@Autowired
	private SpringDataGroupRepository springDataGroupRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Group> getGroup() {
		return groupService.readAllGroups();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Group inputGroup(@RequestBody Group group) {
		return groupService.createGroup(group);
	}

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Group group(@PathVariable("id") long id) {
		return springDataGroupRepository.getOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public Group updateGroup(@PathVariable("id") long id, @RequestBody Group a) {
		return groupService.updateGroup(id, a);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Group deleteGroup(@PathVariable("id") long id) {
		return groupService.deleteGroup(id);
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
	public City getCityByGroup(@PathVariable("id") long id) {
		return (City) groupService.readObjectByGroup(id, new City());
	}

	@RequestMapping(value = "/{id}/city", method = RequestMethod.PUT)
	public Group inputCityByGroup(@PathVariable("id") long id, @RequestBody City city) {
		return groupService.updateGroup(id, "city", city);

	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.GET)
	public List<?> getAllInterestByGroup(@PathVariable("id") long id) {
		return (List<?>) groupService.readObjectByGroup(id, "interest");
	}

	@RequestMapping(value = "/{id}/interests", method = RequestMethod.PUT)
	public Group inputInterestByGroup(@PathVariable("id") long id, @RequestBody Interest interest) {
		return groupService.updateGroup(id, "interest", interest);
		
	}

	@RequestMapping(value = "/{id}/members", method = RequestMethod.GET)
	public List<?> getAllMembersByGroup(@PathVariable("id") long id) {
		return (List<?>) groupService.readObjectByGroup(id, "members");
	}

	@RequestMapping(value = "/{id}/members", method = RequestMethod.PUT)
	public Group inputMembersByGroup(@PathVariable("id") long id, @RequestBody List<?> members) {
	return	groupService.updateGroup(id, "group", members);
	}

	@RequestMapping(value = "/{id}/members/{id2}", method = RequestMethod.DELETE)
	public Group deletePersonByGroup(@PathVariable("id") long id, @PathVariable("id2") long id2) {
		return (Group) groupService.deleteListItemByGroup(id, id2, "members");
	}
}
