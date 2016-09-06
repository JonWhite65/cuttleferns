package com.cooksys.entity;

import javax.persistence.*;


@Entity
public class Interest  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name")
	private String name;
	
	
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
	
	
}
