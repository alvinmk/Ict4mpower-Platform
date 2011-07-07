package templatePanels;

import org.apache.wicket.markup.html.panel.Panel;

public class WorkingObjectPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkingObjectPanel(String id) {
		super(id);
		ClientPanel cp = new ClientPanel("clientPanel");
		VisitPanel vp = new VisitPanel("visitPanel");
		add(cp);
		add(vp);
	}

}
