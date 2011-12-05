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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.ui.dialog.Dialog;

import pagePanels.ClientInfoPanel;

public class ClientPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	public ClientPanel(String id) {
		super(id);
		AppSession s = (AppSession) getSession();

		final Dialog patientDialog = new Dialog("patientDialog");
		Component childs = new ClientInfoPanel("patientData");
		patientDialog.add(childs);
		String name;
		String warnings;
		//Try to read warnings and patient name from the session, set a fallback
		try{
			name = s.getPatientInfo().getName();
		}catch(NullPointerException e){
			name = "No patient selected";
		}
		try{
			warnings = s.getPatientInfo().getWarnings();
		}catch(NullPointerException e){
			warnings = "No warnings";
		}
		   
		patientDialog.setTitle(name);
		AjaxLink<String> patientName = new AjaxLink<String>("clientName") {
			private static final long serialVersionUID = -1999518640202002086L;
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				patientDialog.open(target);
			}
		};
		patientName.add(new Label("patientLinkLabel", name ));
		add(patientName);
		add(patientDialog);
		add( new Label("clientWarnings", warnings ));
	}
}
