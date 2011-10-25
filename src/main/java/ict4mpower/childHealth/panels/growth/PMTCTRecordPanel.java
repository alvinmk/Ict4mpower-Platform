package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

public class PMTCTRecordPanel extends DivisionPanel {
	private static final long serialVersionUID = 3074630914459300687L;
	
	private final List<String> PMTCT = Arrays.asList(new String[] {
			"t",
			"tr",
			"trr",
			"trrd",
			"trrdm",
			"trrdmdb"});

	public PMTCTRecordPanel(String id) {
		super(id, "title");
		
		PMTCTRecordForm form = new PMTCTRecordForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	private class PMTCTRecordForm extends SavingForm {
		private static final long serialVersionUID = -2897098367783146028L;

		public PMTCTRecordForm(String id) {
			super(id);
			
			add(new FeedbackPanel("formFeedback"), false);
			
			GrowthData data = GrowthData.instance();
			
			// PMTCT drop down
			DropDownChoice<String> pmtct = new DropDownChoice<String>("pmtct", new PropertyModel<String>(data, "pmtct"),
					PMTCT) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean localizeDisplayValues() {
					return true;
				}
			};
			add(pmtct);
			
			// HIV test of child
			RadioChoice<String> rc1 = new RadioChoice<String>("hivTestRadio",
					new PropertyModel<String>(data, "hivTestRadio"),
					Arrays.asList(
							new String[]{"hivtest_reactive", "hivtest_non-reactive"})) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean localizeDisplayValues() {
					return true;
				}
			};
			rc1.setSuffix(" ");
			add(rc1);
			
			// Initiation of treatment
			RadioChoice<String> rc2 = new RadioChoice<String>("initTreatmentRadio",
					new PropertyModel<String>(data, "initTreatmentRadio"),
					Arrays.asList(new String[]{"init_treatment_yes", "init_treatment_no"})) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected boolean localizeDisplayValues() {
					return true;
				}
			};
			rc2.setSuffix(" ");
			add(rc2);
			
			// Date of initiation of treatment
			DatePicker<Date> datePicker = new DatePicker<Date>("initTreatmentDate", new PropertyModel<Date>(data, "initTreatmentDate"), Date.class);
			datePicker.add(new ValidationClassBehavior());
			add(datePicker);
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
		}
	}
}
