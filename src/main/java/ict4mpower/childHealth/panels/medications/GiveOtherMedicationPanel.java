package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.panels.DivisionPanel;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

class GiveOtherMedicationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	private TextField<String> drugName;
	private TextField<String> form;
	private TextField<String> dose;
	private TextField<String> reason;
	private TextField<String> instructions;
	
	public GiveOtherMedicationPanel(String id, SavingForm form, DivisionPanel panel) {
		super(id, "medication_admin");
		
		setForm(form, panel);
		
		// Name
		this.drugName = new TextField<String>("drugName", new Model<String>());
		this.drugName.setRequired(true);
		this.drugName.add(new ValidationClassBehavior());
		add(this.drugName);
		
		// Form
		this.form = new TextField<String>("form", new Model<String>());
		this.form.setRequired(true);
		this.form.add(new ValidationClassBehavior());
		add(this.form);
		
		// Dose
		this.dose = new TextField<String>("dose", new Model<String>());
		this.dose.setRequired(true);
		this.dose.add(new ValidationClassBehavior());
		add(this.dose);
		
		// Reason
		this.reason = new TextField<String>("reason", new Model<String>());
		this.reason.setRequired(true);
		this.reason.add(new ValidationClassBehavior());
		add(this.reason);
		
		// Instructions
		this.instructions = new TextField<String>("instructions", new Model<String>());
		this.instructions.setRequired(true);
		this.instructions.add(new ValidationClassBehavior());
		add(this.instructions);
	}

	public TextField<String> getDrugName() {
		return drugName;
	}

	public void setDrugName(TextField<String> drugName) {
		this.drugName = drugName;
	}

	public TextField<String> getForm() {
		return form;
	}

	public void setForm(TextField<String> form) {
		this.form = form;
	}

	public TextField<String> getDose() {
		return dose;
	}

	public void setDose(TextField<String> dose) {
		this.dose = dose;
	}

	public TextField<String> getReason() {
		return reason;
	}

	public void setReason(TextField<String> reason) {
		this.reason = reason;
	}

	public TextField<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(TextField<String> instructions) {
		this.instructions = instructions;
	}
}