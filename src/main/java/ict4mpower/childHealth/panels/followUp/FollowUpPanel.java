package ict4mpower.childHealth.panels.followUp;

import java.sql.Date;

import org.apache.wicket.markup.html.form.TextArea;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import ict4mpower.childHealth.panels.DivisionPanel;

public class FollowUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public FollowUpPanel(String id) {
		super(id, "title");
		
		// Date field
		add(new DatePicker<Date>("datePicker"));
		
		// Text area
		add(new TextArea<String>("textArea"));
	}
}
