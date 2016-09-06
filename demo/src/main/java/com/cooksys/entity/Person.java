package com.cooksys.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Person  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "City")
	private City city;
	
	@OneToMany
    @JoinColumn(name = "Interest")
    private List<Interest> interest;
    
	@ManyToMany
  @JoinTable(name = "Group")
 private List<Group> group;

    
    public Person(){
    }
    
	public Person( String name,City city) {
		
		this.name = name;
		this.city = city;
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

	public List<Interest> getInterest() {
		return interest;
	}

	public void setInterest(List<Interest> interest) {
		this.interest = interest;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}


	
    
}