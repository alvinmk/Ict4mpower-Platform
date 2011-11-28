package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.panels.IDueAge;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import models.PatientInfo;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * A medicine
 * @author Joakim Lindskog
 *
 */
public class Medicine implements Serializable, Comparable<Medicine>, IDueAge {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private PatientInfo patientInfo;
	private String name;
	private Date dueDate;
	private Date givenDate;
	private String dosage;
	private String batchNr;
	// For other medications
	private String form;
	private String reason;
	private String instructions;
	// For standard medicines
	private int calField = -1;
	private int calAdd = -1;
	
	/**
	 * Constructor
	 * @param pi patient info
	 * @param name medicine name
	 * @param calField calendar field to add to the due date (weeks/months etc.)
	 * @param calAdd amount of the calendar field type to add to the due date (e.g. 3 (weeks))
	 * @param givenDate the date the medicine was given
	 * @param dosage the dose of the medicine
	 * @param batchNr the batch number
	 */
	public Medicine(PatientInfo pi, String name, int calField, int calAdd, Date givenDate, String dosage, String batchNr) {
		this.patientInfo = pi;
		this.name = name;
		this.dosage = dosage;
		this.batchNr = batchNr;
		// Calculate due date
		Calendar birth = Calendar.getInstance();
		birth.setTime(patientInfo.getBirthDate());
		birth.add(calField, calAdd);
		this.dueDate = birth.getTime();
		this.givenDate = givenDate;
	}
	
	/**
	 * Constructor for standard (scheduled) medicine
	 * @param name medicine name
	 * @param calField calendar field to add to the due date (weeks/months etc.)
	 * @param calAdd amount of the calendar field type to add to the due date (e.g. 3 (weeks))
	 * @param givenDate the date the medicine was given
	 * @param dosage the dose of the medicine
	 * @param batchNr the batch number
	 */
	public Medicine(String name, int calField, int calAdd, Date givenDate, String dosage, String batchNr) {
		this.name = name;
		this.dosage = dosage;
		this.batchNr = batchNr;
		this.setCalField(calField);
		this.setCalAdd(calAdd);
		this.givenDate = givenDate;
	}
	
	/**
	 * Constructor for other medications
	 * @param name medicine name
	 * @param form medicine form
	 * @param dose dose of the medicine
	 * @param reason reason for giving the medicine
	 * @param instructions instructions for taking the medicine
	 * @param givenDate the date the medicine was given
	 */
	public Medicine(String name, String form, String dose, String reason, String instructions, Date givenDate) {
		this.name = name;
		this.form = form;
		this.dosage = dose;
		this.reason = reason;
		this.instructions = instructions;
		this.givenDate = givenDate;
	}
	
	/**
	 * Gets the due age for these milestone tests
	 * @param parent the parent component
	 * @return a String representation of the due age
	 */
	public String getDueAge(Component parent) {
		return getAgeValue(parent).getObject();
	}
	
	/**
	 * Gets the due age for these milestone tests, as a StringResourceModel
	 * @param parent the parent component
	 * @return a StringResourceModel representation of the due age
	 */
	public StringResourceModel getAgeValue(Component parent) {
		Object[] arr = getAccurateAgeArray();
		return new StringResourceModel((String)arr[0], parent,
				(arr[1] == null ? null : new Model<Float>((float)(0.5f*Math.floor((Float)arr[1]/0.5f)))));
	}
	
	/**
	 * Returns an array representing the age of the child
	 * [0] => properties key for the age unit (weeks/months/years)
	 * [1] => number of units of age (e.g. 3 (weeks))
	 * @return an array representing the age of the child
	 */
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
	
	/**
	 * @return the current status of this medicine (missed/due/taken)
	 */
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
		return batchNr;
	}

	public void setBatchNr(String batchNr) {
		this.batchNr = batchNr;
	}

	public int getCalField() {
		return calField;
	}

	public void setCalField(int calField) {
		this.calField = calField;
	}

	public int getCalAdd() {
		return calAdd;
	}

	public void setCalAdd(int calAdd) {
		this.calAdd = calAdd;
	}

	public int compareTo(Medicine other) {
		return this.dueDate.compareTo(other.dueDate);
	}
}
