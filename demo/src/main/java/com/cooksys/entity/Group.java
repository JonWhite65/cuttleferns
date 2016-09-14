package com.cooksys.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Group {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name = "Name")
	private String name;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "City")
	private City city;
	
	@ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name = "Interest")
  private Interest interest;
    
	@JsonIgnore
  @ManyToMany(mappedBy = "groups")
  private List<Person> members;
    
    public Group(){}
    public Group( String name, City city, Interest interest) {
		
		this.name = name;
		this.city = city;
		this.interest = interest;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public Interest getInterest() {
		return interest;
	}


	public void setInterest(Interest intrest) {
		this.interest = intrest;
	}


	public List<Person> getMembers() {
		return members;
	}


	public void setMembers(List<Person> members) {
		this.members = members;
	}
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(Group.class)) {
			return false;
		} else if (this.getName()!=null&&!((Group) obj).getName().equals(this.getName())) {
			return false;
		} else if (this.getCity()!=null&&!((Group) obj).getCity().equals(this.getCity())){
			return false;
		}
		else if (this.getInterest()!=null&&!((Group) obj).getInterest().equals(this.getInterest())){
			return false;
		}
		else{
			return true;
		}
	}
	
}
