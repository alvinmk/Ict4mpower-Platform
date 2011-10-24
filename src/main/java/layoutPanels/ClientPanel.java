package layoutPanels;

import ict4mpower.AppSession;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.dialog.Dialog;

import PagePanels.ClientInfoPanel;

public class ClientPanel extends Panel {
	private static final long serialVersionUID = 1L;

	
	public ClientPanel(String id) {
		super(id);
		AppSession s = (AppSession) getSession();
		
		//final ModalWindow mw = new ModalWindow("patientDialog");
		//mw.setTitle(new PropertyModel<String>(this ,"session.PatientInfo.getName()" ));
		final Dialog patientDialog = new Dialog("patientDialog");
		add(patientDialog);
		Component childs = new ClientInfoPanel("patientData");
		patientDialog.add(childs);
	
		//Label patientName = new Label("clientName", s.getPatientInfo().getName() );
		//new PropertyModel(this, "session.PatientInfo.Name")
		
		AjaxLink<String> patientName = new AjaxLink<String>("clientName", new PropertyModel<String>(this ,"session.PatientInfo.getName" )) {
			private static final long serialVersionUID = -1999518640202002086L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				AppSession s1 = (AppSession) getSession();
				Component childs = new ClientInfoPanel("patientData");
				patientDialog.setTitle(s1.getPatientInfo().getName());
				patientDialog.addOrReplace(childs);
				patientDialog.open(target);
				//mw.show(new AjaxRequestTarget(ClientInfoPanel.class));
				
			}

			

			
		};

		
		add(patientName);		
		add(patientDialog);
		add( new Label("clientWarnings", s.getPatientInfo().getWarnings() ));
	}

}
