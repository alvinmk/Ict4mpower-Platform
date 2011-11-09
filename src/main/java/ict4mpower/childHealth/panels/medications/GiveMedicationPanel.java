package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.Callback;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.panels.AgeTextDialog;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.CloseButtonCallback;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * Panel for specifying the giving of a medicine
 * @author Joakim Lindskog
 *
 */
class GiveMedicationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	private DropDownChoice<Object[]> ageChoice;
	private DropDownChoice<String> medicineChoice;
	private DropDownChoice<String> dosageChoice;
	private TextField<String> serialNr;
	private AgeTextDialog dialog;
	
	@SuppressWarnings("unused")
	private Object[] ageChosen;
	@SuppressWarnings("unused")
	private String medicineChosen;
	@SuppressWarnings("unused")
	private String dosageChosen;
	
	/**
	 * Constructor
	 * @param id component id
	 * @param ages a list of age values
	 * @param medicines a list of medicine names
	 * @param dosages a list of dosages
	 * @param form the form to use
	 * @param panel the parent panel
	 */
	public GiveMedicationPanel(String id, final List<Object[]> ages, final List<String> medicines,
			final List<String> dosages, SavingForm form, DivisionPanel panel) {
		super(id, "medication_admin");
		
		setForm(form, panel);
		
		ages.add(new Object[]{"age.other", null});
		medicines.add("medication.other");
		dosages.add("dosage.other");
		
		// Age drop down
		this.ageChoice = new DropDownChoice<Object[]>("age", new PropertyModel<Object[]>(this, "ageChosen"),
			ages, new ChoiceRenderer<Object[]>() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public Object getDisplayValue(Object[] object) {
					if(object[1] == null) return getString((String)object[0]);
					else return getString((String)object[0], new Model<Float>((Float)object[1]));
				}
		});
		this.ageChoice.setNullValid(false);
		this.ageChoice.setRequired(true);
		this.ageChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(getAgeChoiceValue() == null) return;
				if(getAgeChoiceValue().equals(getString("age.other"))) {
					// Show input dialog
					dialog.setTitle(new StringResourceModel("other_age", GiveMedicationPanel.this, null));
					dialog.showCalTypes();
					dialog.addOnSubmit(new Callback() {
						private static final long serialVersionUID = 1L;

						public boolean call(AjaxRequestTarget target) {
							String text = dialog.getText();
							int i=-1;
							try {
								i = Integer.parseInt(text);
							} catch(NumberFormatException e) {
								dialog.error(target, "age_name_error");
								return false;
							}
							if(i>=0) {
								Object[] obj = new Object[]{dialog.getCalendarType(), (float)i};
								ages.add(ages.size()-1, obj);
								ageChosen = obj;
								ageChoice.modelChanged();
								target.add(ageChoice);
								dialog.close(target);
							}
							return true;
						}
					});
					dialog.setLabel(getString("age_name"));
					dialog.setCloseButtonCallback(new CloseButtonCallback() {
						private static final long serialVersionUID = 1L;

						public boolean onCloseButtonClicked(AjaxRequestTarget target) {
							ageChosen = null;
							ageChoice.modelChanged();
							target.add(ageChoice);
							return true;
						}
					});
					dialog.show(target);
				}
				target.add(ageChoice);
			}
		});
		this.ageChoice.add(new ValidationClassBehavior());
		add(this.ageChoice);
		
		// Medication drop down
		this.medicineChoice = new DropDownChoice<String>("medication",
				new PropertyModel<String>(this, "medicineChosen"), medicines) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean localizeDisplayValues() {
				return true;
			}
		};
		this.medicineChoice.setNullValid(false);
		this.medicineChoice.setRequired(true);
		this.medicineChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(medicineChoice.getConvertedInput() == null) return;
				if(medicineChoice.getConvertedInput().equals("medication.other")) {
					// Show input dialog
					dialog.setTitle(new StringResourceModel("other_medicine", GiveMedicationPanel.this, null));
					dialog.hideCalTypes();
					dialog.addOnSubmit(new Callback() {
						private static final long serialVersionUID = 1L;

						public boolean call(AjaxRequestTarget target) {
							final String text = dialog.getText();
							if(text == null || text.isEmpty()) {
								dialog.error(target, "medicine_name_error");
								return false;
							}
							medicines.add(medicines.size()-1, text);
							medicineChosen = text;
							medicineChoice.modelChanged();
							target.add(medicineChoice);
							dialog.close(target);
							return true;
						}
					});
					dialog.setLabel(getString("medicine_name"));
					dialog.setCloseButtonCallback(new CloseButtonCallback() {
						private static final long serialVersionUID = 1L;

						public boolean onCloseButtonClicked(AjaxRequestTarget target) {
							medicineChosen = null;
							medicineChoice.modelChanged();
							target.add(medicineChoice);
							return true;
						}
					});
					dialog.show(target);
				}
				target.add(medicineChoice);
			}
		});
		this.medicineChoice.add(new ValidationClassBehavior());
		add(this.medicineChoice);
		
		// Dosage drop down
		this.dosageChoice = new DropDownChoice<String>("dosage",
				new PropertyModel<String>(this, "dosageChosen"), dosages) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean localizeDisplayValues() {
				return true;
			}
		};
		this.dosageChoice.setNullValid(false);
		this.dosageChoice.setRequired(true);
		this.dosageChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(dosageChoice.getConvertedInput() == null) return;
				if(dosageChoice.getConvertedInput().equals("dosage.other")) {
					// Show input dialog
					dialog.setTitle(new StringResourceModel("other_dosage", GiveMedicationPanel.this, null));
					dialog.hideCalTypes();
					dialog.addOnSubmit(new Callback() {
						private static final long serialVersionUID = 1L;

						public boolean call(AjaxRequestTarget target) {
							final String text = dialog.getText();
							if(text == null || text.isEmpty()) {
								dialog.error(target, "dosage_name_error");
								return false;
							}
							dosages.add(dosages.size()-1, text);
							dosageChosen = text;
							dosageChoice.modelChanged();
							target.add(dosageChoice);
							dialog.close(target);
							return true;
						}
					});
					dialog.setLabel(getString("dosage_name"));
					dialog.setCloseButtonCallback(new CloseButtonCallback() {
						private static final long serialVersionUID = 1L;

						public boolean onCloseButtonClicked(AjaxRequestTarget target) {
							dosageChosen = null;
							dosageChoice.modelChanged();
							target.add(dosageChoice);
							return true;
						}
					});
					dialog.show(target);
				}
				target.add(dosageChoice);
			}
		});
		this.dosageChoice.add(new ValidationClassBehavior());
		add(this.dosageChoice);
		
		// Batch number
		this.serialNr = new TextField<String>("batch_nb", new Model<String>());
		this.serialNr.setRequired(true);
		this.serialNr.add(new ValidationClassBehavior());
		add(this.serialNr);
		
		// Dialog
		this.dialog = new AgeTextDialog("dialog");
		
		add(this.dialog);
	}

	public DropDownChoice<Object[]> getAgeChoice() {
		return ageChoice;
	}
	
	public String getAgeChoiceValue() {
		Object[] arr = ageChoice.getConvertedInput();
		return getString((String)arr[0],
				(arr[1] == null ? null : new Model<Float>((float)(0.5f*Math.floor((Float)arr[1]/0.5f)))));
	}

	public DropDownChoice<String> getMedicineChoice() {
		return medicineChoice;
	}

	public DropDownChoice<String> getDosageChoice() {
		return dosageChoice;
	}

	public TextField<String> getSerialNr() {
		return serialNr;
	}
}