package layoutPanels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class VisitPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VisitPanel(String id) {
		super(id);
		add( new Label("visitDate", "DATE"));
		add( new Label("visitStage", "VISIT SIGNED"));
	}
	
}
