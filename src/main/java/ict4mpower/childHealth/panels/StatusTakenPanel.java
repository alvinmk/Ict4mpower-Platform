package ict4mpower.childHealth.panels;

import ict4mpower.WicketApplication;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

public class StatusTakenPanel extends StatusPanel {
	private static final long serialVersionUID = 3478214227782516633L;

	public StatusTakenPanel(String id) {
		super(id);
		
		add(new Image("image", new PackageResourceReference(WicketApplication.class, "images/tick.png")));
	}
}
