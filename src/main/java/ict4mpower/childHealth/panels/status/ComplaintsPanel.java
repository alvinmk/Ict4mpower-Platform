package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;

import ict4mpower.childHealth.panels.DivisionPanel;

public class ComplaintsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public ComplaintsPanel(String id) {
		super(id, "title");
		
		// Complaints
		add(new TextField<String>("complaints_text"));
	}
}
