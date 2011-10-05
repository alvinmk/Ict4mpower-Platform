package ict4mpower.childHealth.panels.medications;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

public class Medicine implements Serializable {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private String age; //TODO remove
	private String name;
	private String dose;
	private Date dueDate;
	private Date givenDate;
	private Panel panel;
	// For other medications
	private String form;
	private String reason;
	private String instructions;
	
	public Medicine(String age, String name, String dose, Date dueDate, Date givenDate, Panel panel) {
		this.age = age;
		this.name = name;
		this.dose = dose;
		this.dueDate = dueDate;
		this.givenDate = givenDate;
		this.panel = panel;
	}
	
	/**
	 * Constructor for other medications
	 * @param name
	 * @param form
	 * @param dose
	 * @param reason
	 * @param instructions
	 * @param panel
	 */
	public Medicine(String name, String form, String dose, String reason, String instructions, Date givenDate, Panel panel) {
		this.name = name;
		this.form = form;
		this.dose = dose;
		this.reason = reason;
		this.instructions = instructions;
		this.givenDate = givenDate;
		this.panel = panel;
	}
	
	public String getAge() {
		if(age.equalsIgnoreCase("0")) return new StringResourceModel("at_birth", panel, null).getObject();
		else if(age.contains("w")) {
			// Weeks
			return age.replace("w", " "+new StringResourceModel("weeks", panel, null).getObject());
		}
		else if(age.contains("m")) {
			// Months
			return age.replace("m", " "+new StringResourceModel("months", panel, null).getObject());
		}
		else return "Error!";
	}
	
	public String getName() {
		return name;
	}
	
	public String getDose() {
		return dose;
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
	
	public String getForm() {
		return form;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getInstructions() {
		return instructions;
	}
}
