package ict4mpower.childHealth.panels;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Base class for status panels
 * @author Joakim Lindskog
 *
 */
public abstract class StatusPanel extends Panel {
	private static final long serialVersionUID = 3478212322277826633L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public StatusPanel(String id) {
		super(id);
	}
}
