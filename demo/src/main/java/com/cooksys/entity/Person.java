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

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "City")
	private City city;
	
	@OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "People_Interest")
    private List<Interest> interest;
    
	@ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "People_Group")
 private List<Group> groups;

    
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
		return groups;
	}

	public void setGroup(List<Group> group) {
		this.groups = group;
	}


	
    
}