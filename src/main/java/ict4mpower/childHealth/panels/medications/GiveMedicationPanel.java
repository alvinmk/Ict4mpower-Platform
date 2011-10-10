package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.StringResourceModel;

class GiveMedicationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	// TODO Dynamic information?
	private final StringResourceModel defaultAgeChoice = new StringResourceModel("dropdown.age.choice.null", this, null);
	private final StringResourceModel defaultMedicationChoice = new StringResourceModel("dropdown.medication.choice.null", this, null);
	private final StringResourceModel defaultDosageChoice = new StringResourceModel("dropdown.dosage.choice.null", this, null);

	public GiveMedicationPanel(String id, List<StringResourceModel> ages, List<StringResourceModel> medications, List<String> dosages) {
		super(id, "medication_admin");
		
		ages.add(0, defaultAgeChoice);
		ages.add(new StringResourceModel("age.other", this, null));
		medications.add(0, defaultMedicationChoice);
		medications.add(new StringResourceModel("medication.other", this, null));
		dosages.add(0, defaultDosageChoice.getObject());
		dosages.add(new StringResourceModel("dosage.other", this, null).getObject());
		
		// Age drop down
		add(new DropDownChoice<StringResourceModel>("age", ages, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultAgeChoice.getObject();
			}
		});
		
		// Medication drop down
		add(new DropDownChoice<StringResourceModel>("medication", medications, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultMedicationChoice.getObject();
			}
		});
		
		// Dosage drop down
		add(new DropDownChoice<String>("dosage", dosages, new ChoiceRenderer<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultDosageChoice.getObject();
			}
		});
		
		// Batch number
		add(new TextField<String>("batch_nb"));
	}
}