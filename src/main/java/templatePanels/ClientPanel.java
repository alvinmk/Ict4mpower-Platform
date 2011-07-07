package templatePanels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class ClientPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ClientPanel(String id) {
		super(id);
		add( new Label("clientName", "CLIENT NAME"));
		add( new Label("clientWarnings", "WARNINGS: WARNINGS"));
	}

}
