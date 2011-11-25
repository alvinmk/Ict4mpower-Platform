package layoutPanels;

import ict4mpower.AppSession;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
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
