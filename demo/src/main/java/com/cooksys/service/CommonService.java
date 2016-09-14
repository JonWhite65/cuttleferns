package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooksys.entity.City;
import com.cooksys.entity.Group;
import com.cooksys.entity.Interest;
import com.cooksys.entity.State;

import org.springframework.stereotype.Service;
import com.cooksys.repository.SpringDataCityRepository;
import com.cooksys.repository.SpringDataGroupRepository;
import com.cooksys.repository.SpringDataInterestRepository;
import com.cooksys.repository.SpringDataPersonRepository;
import com.cooksys.repository.SpringDataStateRepository;
@Service
public class CommonService {
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
	
	public City checkCity(City thisCity){
		List<City> cityList = cityRepo.findAllByName(thisCity.getName());
		
		for (City city : cityList) {
			if (city.equals(thisCity)) {
				if(thisCity.getState()!=null){
					stateRepo.delete(thisCity.getState());
				}
				cityRepo.delete(thisCity);
				thisCity=city;
				break;
			}
		}

		return thisCity;
		
	}
	public State checkState(State thisState){
		List<State> stateList = stateRepo.findAllByName(thisState.getName());
		
		for (State state : stateList) {
			if (state.equals(thisState)) {
				
				stateRepo.delete(thisState);
				thisState=state;
				break;
			}
		}

		return thisState;
		
	}
	public Interest checkInterest(Interest thisInterest){
		List<Interest> interestList = interestRepo.findAllByName(thisInterest.getName());
		
		for (Interest interest : interestList) {
			if (interest.equals(thisInterest)) {
				
				interestRepo.delete(thisInterest);
				thisInterest=interest;
				break;
			}
		}

		return thisInterest;
		
	}
	public Group checkGroup(Group thisGroup){
		List<Group> groupList = groupRepo.findAllByName(thisGroup.getName());
		
		for (Group group : groupList) {
			if (group.equals(thisGroup)) {
				
				groupRepo.delete(thisGroup);
				thisGroup=group;
				break;
			}
		}

		return thisGroup;
		
	}
}
