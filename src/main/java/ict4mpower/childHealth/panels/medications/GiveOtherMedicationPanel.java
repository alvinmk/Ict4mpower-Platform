package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.panels.DivisionPanel;

import org.apache.wicket.markup.html.form.TextField;

class GiveOtherMedicationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	public GiveOtherMedicationPanel(String id) {
		super(id, "medication_admin");
		
		// Name
		add(new TextField<String>("drug_name"));
		
		// Form
		add(new TextField<String>("form"));
		
		// Dose
		add(new TextField<String>("dose"));
		
		// Reason
		add(new TextField<String>("reason"));
		
		// Instructions
		add(new TextField<String>("instructions"));
	}
	
	@Override
	public boolean save() {
		//TODO Hide
		this.setVisible(false);
		return super.save();
	}
}