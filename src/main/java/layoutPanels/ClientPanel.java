package layoutPanels;

import ict4mpower.AppSession;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class ClientPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ClientPanel(String id) {
		super(id);
		AppSession s = (AppSession) getSession();
		add( new Label("clientName", s.getPatientInfo().getName() ));
		add( new Label("clientWarnings", s.getPatientInfo().getWarnings() ));
	}

}
