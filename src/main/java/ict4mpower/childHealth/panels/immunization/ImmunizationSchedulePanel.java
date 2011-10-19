package ict4mpower.childHealth.panels.immunization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.data.ImmunizationData;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.StatusDuePanel;
import ict4mpower.childHealth.panels.StatusMissedPanel;
import ict4mpower.childHealth.panels.StatusPanel;
import ict4mpower.childHealth.panels.StatusTakenPanel;

public class ImmunizationSchedulePanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private ListView<Vaccination> list;
	private GiveVaccinationPanel vaccPanel;

	public ImmunizationSchedulePanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		final ImmunizationForm form = new ImmunizationForm("form");
		form.add(new AjaxFormSubmitBehavior("onsubmit") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target) {
				System.out.println("Error");
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				//TODO Temporary info on age - get due date from database
				System.out.println("age: "+vaccPanel.getAgeChoice().getConvertedInput()
						+" age2: "+list.getModelObject().get(0).getAge()
						+" vaccine: "+vaccPanel.getVaccineChoice().getConvertedInput()
						+" dosage: "+vaccPanel.getDosageChoice().getConvertedInput()
						+" s_nr: "+vaccPanel.getSerialNr().getConvertedInput());
				
				// See if the submitted info matches an item in the list
				List<Vaccination> l = list.getModelObject();
				boolean found = false;
				for(Vaccination v : l) {
					if(v.getAge().equals(vaccPanel.getAgeChoice().getConvertedInput().getObject())
							&& v.getVaccine().equals(vaccPanel.getVaccineChoice().getConvertedInput().getObject())) {
						v.setGivenDate(new Date());
						found = true;
					}
				}
				
				if(!found) {
					try {
						list.getModelObject().add(new Vaccination("5m",
								vaccPanel.getVaccineChoice().getConvertedInput().getObject(),
								df.parse("15/10/2011"),
								new Date(),
								ImmunizationSchedulePanel.this));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				target.add(ImmunizationSchedulePanel.this, ImmunizationSchedulePanel.class.getName().substring(
						ImmunizationSchedulePanel.class.getName().lastIndexOf('.')+1)+"Frame");
			}
		});
		add(form);
	}
	
	private class ImmunizationForm extends SavingForm {
		private static final long serialVersionUID = 8603618882539149364L;

		public ImmunizationForm(String id) {
			super(id);
			
			//TODO Temporary
			List<Vaccination> vaccinations = new ArrayList<Vaccination>();
			try {
				vaccinations.add(new Vaccination("0", "BCG", df.parse("01/08/2011"), df.parse("01/08/2011"), this));
				vaccinations.add(new Vaccination("0", "Oral Polio 0", df.parse("01/08/2011"), df.parse("01/08/2011"), this));
				vaccinations.add(new Vaccination("6w", "Oral Polio 1", df.parse("14/09/2011"), null, this));
				vaccinations.add(new Vaccination("6w", "DPT+HepB+Hib", df.parse("14/09/2011"), df.parse("15/09/2011"), this));
				vaccinations.add(new Vaccination("10w", "Oral Polio 2", df.parse("14/10/2011"), null, this));
				vaccinations.add(new Vaccination("10w", "DPT+HepB+Hib", df.parse("14/10/2011"), null, this));
				vaccinations.add(new Vaccination("14w", "Oral Polio 2", df.parse("14/12/2011"), null, this));
				vaccinations.add(new Vaccination("14w", "DPT+HepB+Hib", df.parse("14/12/2011"), null, this));
				vaccinations.add(new Vaccination("9m", "Measles", df.parse("01/05/2012"), null, this));
			} catch(Exception e) {
				//
			}
			
			ImmunizationData data = ImmunizationData.instance();
			// TODO Temporary
			if(data.getVaccinations() == null) {
				data.setVaccinations(vaccinations);
			}
			
			// Add table items
			list = new ListView<Vaccination>("vaccinations", new PropertyModel<List<Vaccination>>(data, "vaccinations")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Vaccination> item) {
					item.add(new VaccinationPanel("rowPanel", item.getModel()));
				}
			};
			list.setOutputMarkupId(true);
			add(list);
			
			// 'Give vaccination' panel
			vaccPanel = new GiveVaccinationPanel("giveVaccinePanel", this, ImmunizationSchedulePanel.this);
			
			// Add 'give vaccination' button
			add(new AjaxLink<Object>("giveVaccine") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					vaccPanel.setVisible(true);
					target.add(vaccPanel);
				}
			}, false);
			
			// Add 'give vaccination' component
			vaccPanel.setOutputMarkupPlaceholderTag(true);
			vaccPanel.setVisible(false);
			add(vaccPanel, false);
		}
	}
}

class VaccinationPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;

	public VaccinationPanel(String id, IModel<Vaccination> vaccination) {
		super(id);
		
		// Add vaccination values
		Label ageLabel = new Label("age", new PropertyModel<String>(vaccination, "age"));
		add(ageLabel);
		Label vaccineLabel = new Label("vaccine", new PropertyModel<String>(vaccination, "vaccine"));
		add(vaccineLabel);
		StatusPanel statusPanel = null;
		String status = vaccination.getObject().getStatus();
		if(status.contentEquals("missed")) {
			statusPanel = new StatusMissedPanel("status");
		}
		else if(status.contentEquals("taken")) {
			statusPanel = new StatusTakenPanel("status");
		}
		else {
			statusPanel = new StatusDuePanel("status", vaccination.getObject().getDueDate());
		}
		add(statusPanel);
		Label dateLabel = null;
		if(vaccination.getObject().getGivenDate() != null) {
			dateLabel = new Label("dateGiven", new PropertyModel<String>(vaccination, "givenDate"));
		}
		else {
			dateLabel = new Label("dateGiven", "");
		}
		add(dateLabel);
	}
}

class GiveVaccinationPanel extends DivisionPanel {
	private static final long serialVersionUID = 6954282130177169208L;
	
	// TODO Dynamic information?
	private final StringResourceModel defaultAgeChoice = new StringResourceModel("dropdown.age.choice.null", this, null);
	private final StringResourceModel defaultVaccineChoice = new StringResourceModel("dropdown.vaccine.choice.null", this, null);
	private final StringResourceModel defaultDosageChoice = new StringResourceModel("dropdown.dosage.choice.null", this, null);
	
	private final List<StringResourceModel> AGE = Arrays.asList(new StringResourceModel[] {
			defaultAgeChoice,
			new StringResourceModel("at_birth", this, null),
			new StringResourceModel("6w", this, null),
			new StringResourceModel("10w", this, null),
			new StringResourceModel("14w", this, null),
			new StringResourceModel("9m", this, null),
			new StringResourceModel("age.other", this, null)});
	
	private final List<StringResourceModel> VACCINE = Arrays.asList(new StringResourceModel[] {
			defaultVaccineChoice,
			new StringResourceModel("bcg", this, null),
			new StringResourceModel("oral_polio_0", this, null),
			new StringResourceModel("oral_polio_1", this, null),
			new StringResourceModel("oral_polio_2", this, null),
			new StringResourceModel("oral_polio_3", this, null),
			new StringResourceModel("dpt_hepb_hib_1", this, null),
			new StringResourceModel("dpt_hepb_hib_2", this, null),
			new StringResourceModel("dpt_hepb_hib_3", this, null),
			new StringResourceModel("measles", this, null),
			new StringResourceModel("vaccine.other", this, null)});
	
	private final List<String> DOSAGE = Arrays.asList(new String[] {
			defaultDosageChoice.getObject(),
			"0.05 ml",
			"0.1 ml",
			"0.15 ml",
			"0.20 ml",
			new StringResourceModel("dosage.other", this, null).getObject()});
	
	private DropDownChoice<StringResourceModel> ageChoice;
	private DropDownChoice<StringResourceModel> vaccineChoice;
	private DropDownChoice<String> dosageChoice;
	private TextField<String> serialNr;

	public GiveVaccinationPanel(String id, Form<?> form, Panel panel) {
		super(id, "vaccine_admin");
		
		setForm(form, panel);
		
		// Age drop down
		this.ageChoice = new DropDownChoice<StringResourceModel>("age", AGE, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultAgeChoice.getObject();
			}
		};
		add(this.ageChoice);
		
		// Vaccine drop down
		this.vaccineChoice = new DropDownChoice<StringResourceModel>("vaccine", VACCINE, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultVaccineChoice.getObject();
			}
		};
		add(this.vaccineChoice);
		
		// Dosage drop down
		this.dosageChoice = new DropDownChoice<String>("dosage", DOSAGE, new ChoiceRenderer<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultDosageChoice.getObject();
			}
		};
		add(this.dosageChoice);
		
		// Vaccine serial number
		this.serialNr = new TextField<String>("vaccine_sn", new Model<String>());
		add(this.serialNr);
	}

	public DropDownChoice<StringResourceModel> getAgeChoice() {
		return ageChoice;
	}

	public DropDownChoice<StringResourceModel> getVaccineChoice() {
		return vaccineChoice;
	}

	public DropDownChoice<String> getDosageChoice() {
		return dosageChoice;
	}

	public TextField<String> getSerialNr() {
		return serialNr;
	}
}