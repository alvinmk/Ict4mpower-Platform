package ict4mpower.childHealth.panels.immunization;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.Component;

public class Vaccination implements Serializable {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private String age;
	private String vaccine;
	private Date dueDate;
	private Date givenDate;
	private Component parent;
	
	public Vaccination(String age, String vaccine, Date dueDate, Date givenDate, Component parent) {
		this.age = age;
		this.vaccine = vaccine;
		this.dueDate = dueDate;
		this.givenDate = givenDate;
		this.parent = parent;
	}
	
	public void setGivenDate(Date date) {
		this.givenDate = date;
	}

	public String getAge() {
		if(age.equalsIgnoreCase("0")) return parent.getString("at_birth");
		else if(age.contains("w")) {
			// Weeks
			return age.replace("w", " "+parent.getString("weeks"));
		}
		else if(age.contains("m")) {
			// Months
			return age.replace("m", " "+parent.getString("months"));
		}
		else return "Error!";
	}
	
	public String getVaccine() {
		return vaccine;
	}
	
	public String getStatus() {
		if(givenDate == null) {
			if(dueDate.before(new Date())) {
				return "missed";
			}
			else return "due";
		}
		else return "taken";
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public String getGivenDate() {
		if(givenDate != null) {
			return df.format(givenDate);
		}
		else return "";
	}
}