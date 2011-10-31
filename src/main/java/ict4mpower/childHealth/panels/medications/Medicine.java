package ict4mpower.childHealth.panels.medications;

import ict4mpower.Person;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class Medicine implements Serializable, Comparable<Medicine> {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private Person person;
	private String name;
	private Date dueDate;
	private Date givenDate;
	private String dosage;
	private String serialNr;
	private Component parent;
	// For other medications
	private String form;
	private String reason;
	private String instructions;
	
	public Medicine(Person p, String name, int calField, int calAdd, Date givenDate, String dosage, String serialNr, Component parent) {
		this.person = p;
		this.name = name;
		this.dosage = dosage;
		this.serialNr = serialNr;
		// Calculate due date
		Calendar birth = Calendar.getInstance();
		birth.setTime(person.getBirth());
		birth.add(calField, calAdd);
		this.dueDate = birth.getTime();
		this.givenDate = givenDate;
		this.parent = parent;
	}
	
	/**
	 * Constructor for other medications
	 * @param name
	 * @param form
	 * @param dose
	 * @param reason
	 * @param instructions
	 * @param parent
	 */
	public Medicine(String name, String form, String dose, String reason, String instructions, Date givenDate, Component parent) {
		this.name = name;
		this.form = form;
		this.dosage = dose;
		this.reason = reason;
		this.instructions = instructions;
		this.givenDate = givenDate;
		this.parent = parent;
	}
	
	public String getDueAge() {
		return getAgeValue().getObject();
	}
	
	public StringResourceModel getAgeValue() {
		Object[] arr = getAccurateAgeArray();
		return new StringResourceModel((String)arr[0], parent,
				(arr[1] == null ? null : new Model<Float>((float)(0.5f*Math.floor((Float)arr[1]/0.5f)))));
	}
	
	public Object[] getAccurateAgeArray() {
		Calendar due = Calendar.getInstance();
		due.setTime(dueDate);
		Calendar birth = Calendar.getInstance();
		birth.setTime(person.getBirth());
		int years = due.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		int months = due.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
		int days = due.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);
		int extraDays = 0;
		
		// Check days
		if(days < 0) {
			months += years*12-1;
		}
		else {
			months += years*12;
		}
		Calendar b = (Calendar) birth.clone();
		b.add(Calendar.MONTH, months);
		extraDays = due.get(Calendar.DAY_OF_MONTH) - b.get(Calendar.DAY_OF_MONTH);
		if(extraDays<0) {
			extraDays += b.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		if(months < 3) {
			// Less than 3 months old
			int weeks = due.get(Calendar.WEEK_OF_YEAR) - birth.get(Calendar.WEEK_OF_YEAR);
			if(weeks == 0) {
				// At birth
				return new Object[]{"at_birth", null};
			}
			if(weeks<0) {
				weeks += birth.getActualMaximum(Calendar.WEEK_OF_YEAR);
			}
			b = (Calendar) birth.clone();
			b.add(Calendar.WEEK_OF_YEAR, weeks);
			extraDays = due.get(Calendar.DAY_OF_WEEK) - b.get(Calendar.DAY_OF_WEEK);
			if(extraDays<0) {
				extraDays += 7;
				weeks -= 1;
			}
			return new Object[]{"weeks", weeks+extraDays/7f};
		}
		else if(months >= 6) {
			// More than or equal to 2 years old, use years
			return new Object[]{"years", months/12f+extraDays/(float)due.getActualMaximum(Calendar.DAY_OF_YEAR)};
		}
		else {
			// More than or equal to 3 months old but less than 6 months old, use months
			return new Object[]{"months", months+extraDays/(float)due.getActualMaximum(Calendar.DAY_OF_MONTH)};
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
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
	
	public Date getGivenDate() {
		return givenDate;
	}
	
	public void setGivenDate(Date date) {
		this.givenDate = date;
	}
	
	public String getGivenDateString() {
		return df.format(givenDate);
	}
	
	public String getForm() {
		return form;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getInstructions() {
		return instructions;
	}

	public String getBatchNr() {
		return serialNr;
	}

	public void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
	}

	public int compareTo(Medicine other) {
		return this.dueDate.compareTo(other.dueDate);
	}
}
