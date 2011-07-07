package templatePanels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class UserPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UserPanel(String id) {
		super(id);
		add( new Label("username", "username"));
		add( new Label("applicationSelect", "Current Application"));
		add( new Label("exitApplication", "Exit"));
	}

	

}
