package ict4mpower.childHealth.panels.growth;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import storage.DataEndPoint;

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
			if(data.getPmtct() == null || data.getHivTestRadio() == null || data.getInitTreatmentRadio() == null
					|| data.getInitTreatmentDate() == null) {
				Date max = null;
				try {
					max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				GrowthData gd = null;
				// Get from db
				Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
				for(Object o : set) {
					if(o instanceof GrowthData) {
						gd = (GrowthData) o;
						if(gd.getDate().after(max)) {
							if(gd.getPmtct() != null) {
								data.setPmtct(gd.getPmtct());
							}
							if(gd.getHivTestRadio() != null) {
								data.setHivTestRadio(gd.getHivTestRadio());
							}
							if(gd.getInitTreatmentRadio() != null) {
								data.setInitTreatmentRadio(gd.getInitTreatmentRadio());
							}
							if(gd.getInitTreatmentDate() != null) {
								data.setInitTreatmentDate(gd.getInitTreatmentDate());
							}
							max = gd.getDate();
						}
					}
				}
			}
			
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
			GrowthData.instance().setDate(new Date());
			
			super.onSubmit();
		}
	}
}