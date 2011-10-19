package ict4mpower.childHealth.panels.followUp;

import java.util.Date;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class FollowUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private DatePicker<Date> date;
	private TextArea<String> message;

	public FollowUpPanel(String id) {
		super(id, "title");
		
		FollowUpForm form = new FollowUpForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	private class FollowUpForm extends SavingForm {
		private static final long serialVersionUID = -2039157252043048544L;

		public FollowUpForm(String id) {
			super(id);
			
			FollowUpData data = FollowUpData.instance();
			
			// Date field
			date = new DatePicker<Date>("date", new PropertyModel<Date>(data, "date"));
			add(date);
			
			// Text area
			message = new TextArea<String>("message", new PropertyModel<String>(data, "message"));
			add(message);
		}
	}
}
