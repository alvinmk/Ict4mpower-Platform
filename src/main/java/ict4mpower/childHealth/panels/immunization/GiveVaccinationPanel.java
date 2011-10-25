package ict4mpower.childHealth.panels.immunization;

import ict4mpower.childHealth.Callback;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.util.ArrayList;
import java.util.Arrays;
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

public class GiveVaccinationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	private final List<Object[]> ages;
	private final List<String> vaccines;
	private final List<String> dosages;
	
	private DropDownChoice<Object[]> ageChoice;
	private DropDownChoice<String> vaccineChoice;
	private DropDownChoice<String> dosageChoice;
	private TextField<String> serialNr;
	private ImmunizationDialog dialog;
	
	@SuppressWarnings("unused")
	private String vaccineChosen;
	@SuppressWarnings("unused")
	private String dosageChosen;
	@SuppressWarnings("unused")
	private Object[] ageChosen;

	public GiveVaccinationPanel(String id, SavingForm form, DivisionPanel panel) {
		super(id, "vaccine_admin");
		
		setForm(form, panel);
		
		// Age drop down
		this.ages = new ArrayList<Object[]>(
				Arrays.asList(new Object[][] {
					new Object[]{"at_birth", null},
					new Object[]{"weeks", 6},
					new Object[]{"weeks", 10},
					new Object[]{"months", 3},
					new Object[]{"months", 9},
					new Object[]{"age.other", null}}));
		this.ageChoice = new DropDownChoice<Object[]>("age",
				new PropertyModel<Object[]>(this, "ageChosen"),
				ages, new ChoiceRenderer<Object[]>() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public Object getDisplayValue(Object[] object) {
						if(object[1] == null) return getString((String)object[0]);
						else return getString((String)object[0], new Model<Integer>((Integer)object[1]));
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
					dialog.setTitle(new StringResourceModel("other_age", GiveVaccinationPanel.this, null));
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
								Object[] obj = new Object[]{dialog.getCalendarType(), i};
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
		
		// Vaccine drop down
		this.vaccines =  new ArrayList<String>(
				Arrays.asList(new String[] {
				"bcg",
				"oral_polio_0",
				"oral_polio_1",
				"oral_polio_2",
				"oral_polio_3",
				"dpt_hepb_hib_1",
				"dpt_hepb_hib_2",
				"dpt_hepb_hib_3",
				"measles",
				"other"}));
		this.vaccineChoice = new DropDownChoice<String>("vaccine",
				new PropertyModel<String>(this, "vaccineChosen"), vaccines) {
			private static final long serialVersionUID = -3843704767044766449L;

			@Override
			protected boolean localizeDisplayValues() {
				return true;
			}
		};
		this.vaccineChoice.setNullValid(false);
		this.vaccineChoice.setRequired(true);
		this.vaccineChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(vaccineChoice.getConvertedInput() == null) return;
				if(vaccineChoice.getConvertedInput().equals("other")) {
					// Show input dialog
					dialog.setTitle(new StringResourceModel("other_vaccine", GiveVaccinationPanel.this, null));
					dialog.hideCalTypes();
					dialog.addOnSubmit(new Callback() {
						private static final long serialVersionUID = 1L;

						public boolean call(AjaxRequestTarget target) {
							final String text = dialog.getText();
							if(text == null || text.isEmpty()) {
								dialog.error(target, "vaccine_name_error");
								return false;
							}
							vaccines.add(vaccines.size()-1, text);
							vaccineChosen = text;
							vaccineChoice.modelChanged();
							target.add(vaccineChoice);
							dialog.close(target);
							return true;
						}
					});
					dialog.setLabel(getString("vaccine_name"));
					dialog.setCloseButtonCallback(new CloseButtonCallback() {
						private static final long serialVersionUID = 1L;

						public boolean onCloseButtonClicked(AjaxRequestTarget target) {
							vaccineChosen = null;
							vaccineChoice.modelChanged();
							target.add(vaccineChoice);
							return true;
						}
					});
					dialog.show(target);
				}
				target.add(vaccineChoice);
			}
		});
		this.vaccineChoice.add(new ValidationClassBehavior());
		add(this.vaccineChoice);
		
		// Dosage drop down
		this.dosages = new ArrayList<String>(
				Arrays.asList(new String[] {
					"0.05ml",
					"0.10ml",
					"0.15ml",
					"0.20ml",
					"other"}));
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
				if(dosageChoice.getConvertedInput().equals("other")) {
					// Show input dialog
					dialog.setTitle(new StringResourceModel("other_dosage", GiveVaccinationPanel.this, null));
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
		
		// Vaccine serial number
		this.serialNr = new TextField<String>("vaccine_sn", new Model<String>());
		this.serialNr.setRequired(true);
		this.serialNr.add(new ValidationClassBehavior());
		add(this.serialNr);
		
		// Dialog
		this.dialog = new ImmunizationDialog("dialog");
		
		add(this.dialog);
	}

	public DropDownChoice<Object[]> getAgeChoice() {
		return ageChoice;
	}
	
	public String getAgeChoiceValue() {
		return getString((String)ageChoice.getConvertedInput()[0],
				new Model<Integer>((Integer)ageChoice.getConvertedInput()[1]));
	}

	public DropDownChoice<String> getVaccineChoice() {
		return vaccineChoice;
	}

	public DropDownChoice<String> getDosageChoice() {
		return dosageChoice;
	}

	public TextField<String> getSerialNr() {
		return serialNr;
	}
}