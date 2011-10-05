package ict4mpower.childHealth.panels.dash;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import ict4mpower.childHealth.panels.DivisionPanel;

public class SpecialCarePanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public SpecialCarePanel(String id) {
		super(id, "title", false);
		
		// Reasons text
		// TODO Fetch info for special care reasons
		add(new Label("reasonsText", new Model<String>("Reasons for special care should be here...")));
	}
}
