package com.cooksys.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Group {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "City")
	private City city;
	
	@ManyToOne
   @JoinColumn(name = "Interest")
  private Interest interest;
    

  @ManyToMany(mappedBy = "group")
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
	
}
