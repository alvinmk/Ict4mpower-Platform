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
package layoutPanels;

import ict4mpower.AppSession;

import java.util.Arrays;
import java.util.List;

import layout.Template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class UserPanel extends Panel {
	private static final long serialVersionUID = -4733602128670464335L;
	
	private List<String> Apps = Arrays.asList(new String[] { "Current Application", "Other Application", "Another Application" });
	public String selected ="";
	
	public UserPanel(String id, String selectedApplication) {
		super(id);
		selected = selectedApplication;
		AppSession s = (AppSession) getSession();
		add( new Label("username", s.getUserID()));
		
		DropDownChoice<String> choice = new DropDownChoice<String>("applicationSelect", new PropertyModel<String>(this, "selected"), Apps);	
		add( choice);
		
		add( new Label("location", "Kampala, HC4"));
		add( new Button("exitApplication"){
			private static final long serialVersionUID = 682725950045799885L;

			@Override
			public void onSubmit() {
				setResponsePage(Template.class);
			}
		});
	}
}
