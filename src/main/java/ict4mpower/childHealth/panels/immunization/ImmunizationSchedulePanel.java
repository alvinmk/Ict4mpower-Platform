package ict4mpower.childHealth.panels.immunization;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.StatusDuePanel;
import ict4mpower.childHealth.panels.StatusMissedPanel;
import ict4mpower.childHealth.panels.StatusPanel;
import ict4mpower.childHealth.panels.StatusTakenPanel;

public class ImmunizationSchedulePanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");

	public ImmunizationSchedulePanel(String id) {
		super(id, "title", false);
		
		//TODO Temporary
		List<Vaccination> vaccinations = null;
		try {
			vaccinations = Arrays.asList(new Vaccination[]{
					new Vaccination("0", "BCG", df.parse("01/08/2011"), df.parse("01/08/2011"), this),
					new Vaccination("0", "Oral Polio 0", df.parse("01/08/2011"), df.parse("01/08/2011"), this),
					new Vaccination("6w", "Oral Polio 1", df.parse("14/09/2011"), null, this),
					new Vaccination("6w", "DPT+HepB+Hib", df.parse("14/09/2011"), df.parse("15/09/2011"), this),
					new Vaccination("10w", "Oral Polio 2", df.parse("14/10/2011"), null, this),
					new Vaccination("10w", "DPT+HepB+Hib", df.parse("14/10/2011"), null, this),
					new Vaccination("14w", "Oral Polio 2", df.parse("14/12/2011"), null, this),
					new Vaccination("14w", "DPT+HepB+Hib", df.parse("14/12/2011"), null, this),
					new Vaccination("9m", "Measles", df.parse("01/05/2012"), null, this)
			});
		} catch(Exception e) {
			//
		}
		
		// Add table items
		add(new ListView<Vaccination>("vaccinations", vaccinations) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Vaccination> item) {
				Vaccination vacc = item.getModelObject();
				item.add(new VaccinationPanel("rowPanel", vacc));
			}
		});
		
		// 'Give vaccionation' panel
		final GiveVaccinationPanel vPanel = new GiveVaccinationPanel("giveVaccinePanel");
		
		// Add 'give vaccination' button
		add(new AjaxLink<Object>("giveVaccine") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				vPanel.setVisible(true);
				target.addComponent(vPanel);
			}
		});
		
		// Add 'give vaccination' component
		vPanel.setOutputMarkupPlaceholderTag(true);
		vPanel.setVisible(false);
		add(vPanel);
	}
}

class Vaccination implements Serializable {
	private static final long serialVersionUID = -4299959951236886609L;
	
	private String age;
	private String vaccine;
	private Date dueDate;
	private Date givenDate;
	private Panel panel;
	
	public Vaccination(String age, String vaccine, Date dueDate, Date givenDate, ImmunizationSchedulePanel panel) {
		this.age = age;
		this.vaccine = vaccine;
		this.dueDate = dueDate;
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
	
	public Date getGivenDate() {
		return givenDate;
	}
}

class VaccinationPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public VaccinationPanel(String id, Vaccination vaccination) {
		super(id);
		
		// Add vaccination values
		Label ageLabel = new Label("age", new Model<String>(vaccination.getAge()));
		add(ageLabel);
		Label vaccineLabel = new Label("vaccine", new Model<String>(vaccination.getVaccine()));
		add(vaccineLabel);
		StatusPanel statusPanel = null;
		String status = vaccination.getStatus();
		if(status.contentEquals("missed")) {
			statusPanel = new StatusMissedPanel("status");
		}
		else if(status.contentEquals("taken")) {
			statusPanel = new StatusTakenPanel("status");
		}
		else {
			statusPanel = new StatusDuePanel("status", vaccination.getDueDate());
		}
		add(statusPanel);
		Label dateLabel = null;
		if(vaccination.getGivenDate() != null) {
			dateLabel = new Label("dateGiven", new Model<String>(df.format(vaccination.getGivenDate())));
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

	public GiveVaccinationPanel(String id) {
		super(id, "vaccine_admin");
		
		// Age drop down
		add(new DropDownChoice<StringResourceModel>("age", AGE, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultAgeChoice.getObject();
			}
		});
		
		// Vaccine drop down
		add(new DropDownChoice<StringResourceModel>("vaccine", VACCINE, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultVaccineChoice.getObject();
			}
		});
		
		// Dosage drop down
		add(new DropDownChoice<String>("dosage", DOSAGE, new ChoiceRenderer<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultDosageChoice.getObject();
			}
		});
		
		// Vaccine serial number
		add(new TextField<String>("vaccine_sn"));
	}
	
	@Override
	public boolean save() {
		//TODO Hide
		this.setVisible(false);
		return super.save();
	}
}