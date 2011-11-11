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

import ict4mpower.childHealth.data.CheckInfoData;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A panel for the StatusPraesens task with a checkbox and a toggling input field
 * @author Joakim Lindskog
 *
 */
public class CheckInfoPanel extends Panel {
	private static final long serialVersionUID = 827830474865500102L;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 */
	public CheckInfoPanel(String id, IModel<CheckInfoData> model) {
		super(id);
		
		// Add check box, label and text field
		CheckBox check = new CheckBox("check", new PropertyModel<Boolean>(model, "check"));
		final TextField<String> input = new TextField<String>("info", new PropertyModel<String>(model, "info"), String.class);
		input.setOutputMarkupPlaceholderTag(true);
		input.setVisible(model.getObject().isCheck());
		check.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				if(input.isVisible()) {
					input.setVisible(false);
				}
				else {
					input.setVisible(true);
					target.focusComponent(input);
				}
				target.add(input);
			}
		});
		add(check);
		add(new Label("label", model.getObject().getLabel()));
		add(input);
	}
}
