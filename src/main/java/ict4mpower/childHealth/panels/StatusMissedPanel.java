/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
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
