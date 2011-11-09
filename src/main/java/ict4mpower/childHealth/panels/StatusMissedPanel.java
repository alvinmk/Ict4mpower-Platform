package ict4mpower.childHealth.panels;

import ict4mpower.WicketApplication;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Panel to indicate a missed vaccination or medication
 * @author Joakim Lindskog
 *
 */
public class StatusMissedPanel extends StatusPanel {
	private static final long serialVersionUID = 3478223782516633L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public StatusMissedPanel(String id) {
		super(id);
		
		add(new Image("image", new PackageResourceReference(WicketApplication.class, "images/attention.gif")));
	}
}
