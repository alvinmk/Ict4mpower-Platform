package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

public class PMTCTRecordPanel extends DivisionPanel {
	private static final long serialVersionUID = 3074630914459300687L;
	
	private final StringResourceModel defaultChoice = new StringResourceModel("dropdown.choice.null", this, null);
	private final List<StringResourceModel> PMTCT = Arrays.asList(new StringResourceModel[] {
			defaultChoice,
			new StringResourceModel("t", this, null),
			new StringResourceModel("tr", this, null),
			new StringResourceModel("trr", this, null),
			new StringResourceModel("trrd", this, null),
			new StringResourceModel("trrdm", this, null),
			new StringResourceModel("trrdmdb", this, null)});

	public PMTCTRecordPanel(String id) {
		super(id, "title");
		
		PMTCTRecordForm form = new PMTCTRecordForm("form");
		add(form);
		
		setForm(form, this.getClass().getName()+"Frame");
	}
	
	private class PMTCTRecordForm extends SavingForm {
		private static final long serialVersionUID = -2897098367783146028L;

		public PMTCTRecordForm(String id) {
			super(id, PMTCTRecordForm.class.getName());
			
			PMTCTRecordData data = new PMTCTRecordData();
			
			// PMTCT drop down
			add(new DropDownChoice<StringResourceModel>("pmtct", new PropertyModel<StringResourceModel>(data, "pmtct"),
					PMTCT, new StringResourceModelChoiceRenderer()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected CharSequence getDefaultChoice(String arg0) {
					return defaultChoice.getObject();
				}
			});
			// Other choices
			RadioChoice<StringResourceModel> rc1 = new RadioChoice<StringResourceModel>("hivtest_radio",
					new PropertyModel<StringResourceModel>(data, "hivTest"),
					Arrays.asList(new StringResourceModel[]{new StringResourceModel("hivtest_reactive", this, null), new StringResourceModel("hivtest_non-reactive", this, null)}),
					new StringResourceModelChoiceRenderer());
			rc1.setSuffix(" ");
			add(rc1);
			RadioChoice<StringResourceModel> rc2 = new RadioChoice<StringResourceModel>("init_treatment_radio",
					new PropertyModel<StringResourceModel>(data, "initTreatment"),
					Arrays.asList(new StringResourceModel[]{new StringResourceModel("init_treatment_yes", this, null), new StringResourceModel("init_treatment_no", this, null)}),
					new StringResourceModelChoiceRenderer());
			rc2.setSuffix(" ");
			add(rc2);
			// Date of initiation of treatment
			add(new DatePicker<Date>("init_treatment_date", new PropertyModel<Date>(data, "date")));
		}
	}
}
