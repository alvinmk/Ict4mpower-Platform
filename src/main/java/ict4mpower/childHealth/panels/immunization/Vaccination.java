package ict4mpower.childHealth.panels.immunization;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import models.PatientInfo;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class Vaccination implements Serializable, Comparable<Vaccination> {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private PatientInfo patientInfo;
	private String vaccine;
	private Date dueDate;
	private Date givenDate;
	private String dosage;
	private String serial_nr;
	
	public Vaccination(PatientInfo pi, String vaccine, int calField, int calAdd, Date givenDate, String dosage,
			String serial_nr) {
		this.patientInfo = pi;
		this.vaccine = vaccine;
		// Calculate due date
		Calendar birth = Calendar.getInstance();
		birth.setTime(patientInfo.getBirthDate());
		birth.add(calField, calAdd);
		this.dueDate = birth.getTime();
		this.givenDate = givenDate;
		this.dosage = dosage;
		this.serial_nr = serial_nr;
	}
	
	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo pi) {
		this.patientInfo = pi;
	}

	public void setGivenDate(Date date) {
		this.givenDate = date;
	}

	public String getDueAge(Component parent) {
		return getAgeValue(parent).getObject();
	}
	
	public StringResourceModel getAgeValue(Component parent) {
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
		else return null;
	}
	
	public Date getGivenDateValue() {
		return givenDate;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getSerial_nr() {
		return serial_nr;
	}

	public void setSerial_nr(String serial_nr) {
		this.serial_nr = serial_nr;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int compareTo(Vaccination other) {
		return this.dueDate.compareTo(other.dueDate);
	}
}