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

import ict4mpower.childHealth.data.CheckableOption;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A panel for the Education task with a checkbox and a label
 * @author Joakim Lindskog
 *
 */
public class CheckableOptionPanel extends Panel {
	private static final long serialVersionUID = 8243634455154294405L;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param parent parent component
	 */
	public CheckableOptionPanel(String id, IModel<CheckableOption> model, Component parent) {
		super(id);
		// Add check
		add(new CheckBox("check", new PropertyModel<Boolean>(model, "checked")));
		
		// Add text
		add(new Label("option", parent.getString(model.getObject().getOption())));
	}
}