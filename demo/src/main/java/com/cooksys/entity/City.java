package com.cooksys.entity;

import javax.persistence.*;


@Entity
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="State")
	private State state;
	
	public City(){}
	public City( String name, State state) {
	
		this.name = name;
		//this.state = state;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
