package models;

import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = 959870896637493090L;
	private String name;
	private String lastName;
  
	public Person(){
	  this.name = "not set";
	}

	public void setName(String name){
		this.name = name;
	}
  
	public String getName(){
		return name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
}
