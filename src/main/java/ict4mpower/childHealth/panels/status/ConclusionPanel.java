package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;

import ict4mpower.childHealth.panels.DivisionPanel;

public class ConclusionPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public ConclusionPanel(String id) {
		super(id, "title");
		
		// Conclusion
		add(new TextField<String>("conclusion_text"));
	}
}
