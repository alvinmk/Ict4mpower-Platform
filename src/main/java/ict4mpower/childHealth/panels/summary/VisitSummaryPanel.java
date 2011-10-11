package ict4mpower.childHealth.panels.summary;

import org.apache.wicket.markup.html.basic.Label;

import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.followUp.FollowUpData;

public class VisitSummaryPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172223673548889654L;

	public VisitSummaryPanel(String id) {
		super(id, "title");
		
		// Test
		FollowUpData followUp = (FollowUpData) getSession().getAttribute("childHealth.FollowUpPanel");
		add(new Label("test", followUp.getDate()+" - "+followUp.getMessage()));
	}
}
