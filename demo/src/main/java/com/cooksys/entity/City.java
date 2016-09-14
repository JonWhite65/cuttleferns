package com.cooksys.entity;


import javax.persistence.*;




@Entity
public class City { 
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name="state")
	private State state;
	
	public City(){}
	
	public City( String name, State state) {
		this.name = name;
		this.state = state;
		
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
	@Override
	public boolean equals(Object obj){
		if(obj.getClass()!= City.class){
		return false;
		}
		else if(this.getName()!=null&&!((City)obj).getName().equals(this.getName())){
			return false;
		}
		else if(this.getState()!=null&&!((City)obj).getState().equals(this.getState())){
			return false;
		}
		else if((this.getState()==null&&((City)obj).getState()!=null)||(this.getName()==null&&((City)obj).getName()!=null)) {
			return false;
			
		}
		return true;
	}

}
