package ict4mpower.childHealth.panels.development;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import models.PatientInfo;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class MilestoneTests implements Serializable, Cloneable {
	private static final long serialVersionUID = 4401388786490151969L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private PatientInfo patientInfo;
	private Date dueDate;
	public String grossMotor;
	public String fineMotor;
	public String communication;
	public String cognitive;
	
	public String grossChoice;
	public String fineChoice;
	public String commChoice;
	public String cogChoice;
	public String hearLeftChoice;
	public String hearRightChoice;
	public String eyeLeftChoice;
	public String eyeRightChoice;
	
	private Component parent;
	
	public MilestoneTests(PatientInfo pi, int calField, int calAdd, String grossMotor, String fineMotor,
			String communication, String cognitive, Component parent) {
		this.patientInfo = pi;
		// Calculate due date
		Calendar birth = Calendar.getInstance();
		birth.setTime(pi.getBirthDate());
		birth.add(calField, calAdd);
		this.dueDate = birth.getTime();
		this.grossMotor = grossMotor;
		this.fineMotor = fineMotor;
		this.communication = communication;
		this.cognitive = cognitive;
		this.parent = parent;
	}
	
	public String getDueAge() {
		return getAgeValue().getObject();
	}
	
	public StringResourceModel getAgeValue() {
		Object[] arr = getAccurateAgeArray();
		return new StringResourceModel((String)arr[0], parent,
				(arr[1] == null ? null : new Model<Integer>((int)Math.floor((Float)arr[1]))));
	}
	
	public Object[] getAccurateAgeArray() {
		Calendar due = Calendar.getInstance();
		due.setTime(dueDate);
		Calendar birth = Calendar.getInstance();
		birth.setTime(patientInfo.getBirthDate());
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
		else if(months >= 24) {
			// More than or equal to 2 years old, use years
			return new Object[]{"years", months/12f+extraDays/(float)due.getActualMaximum(Calendar.DAY_OF_YEAR)};
		}
		else {
			// More than or equal to 3 months old, use months
			return new Object[]{"months", months+extraDays/(float)due.getActualMaximum(Calendar.DAY_OF_MONTH)};
		}
	}
	
	public String getDueDateString() {
		return df.format(this.dueDate);
	}

	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date date) {
		this.dueDate = date;
	}
	
	public void resetChoices() {
		this.grossChoice = null;
		this.fineChoice = null;
		this.commChoice = null;
		this.cogChoice = null;
		this.hearLeftChoice = null;
		this.hearRightChoice = null;
		this.eyeLeftChoice = null;
		this.eyeRightChoice = null;
	}
	
	@Override
	protected MilestoneTests clone()  {
		try {
			return (MilestoneTests) super.clone();
		} catch(CloneNotSupportedException e) {
			return null;
		}
	}
}