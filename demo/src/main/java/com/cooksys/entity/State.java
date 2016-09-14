package com.cooksys.entity;

import javax.persistence.*;

@Entity
public class  State {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;
	
	public State(){
		
	}
	public State( String name) {
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
		if (!obj.getClass().equals(State.class)) {
			return false;
		} else if (this.getName()!=null&&!((State) obj).getName().equals(this.getName())) {
			return false;
		} else {
			return true;
		}
	}

}
