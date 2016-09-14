package com.cooksys.entity;

import java.util.List;

import javax.persistence.*;


@Entity
public class Interest  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name")
	private String name;
	
	@OneToMany(mappedBy = "interest")
	private List<Person> persons;
	
	@OneToMany(mappedBy = "interest")
	private List<Group> groups;
	
	public Interest(){
		
	}
	public Interest( String name) {
		this.name = name;
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

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(Interest.class)) {
			return false;
		} else if (this.getName()!=null&&!((Interest) obj).getName().equals(this.getName())) {
			return false;
		} else {
			return true;
		}
	}
	
}
