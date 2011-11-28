package ict4mpower.childHealth.panels;

import ict4mpower.WicketApplication;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Panel to indicate a vaccination or medication has been taken
 * @author Joakim Lindskog
 *
 */
public class StatusTakenPanel extends StatusPanel {
	private static final long serialVersionUID = 3478214227782516633L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public StatusTakenPanel(String id) {
		super(id);
		
		add(new Image("image", new PackageResourceReference(WicketApplication.class, "images/tick.png")));
	}
}
