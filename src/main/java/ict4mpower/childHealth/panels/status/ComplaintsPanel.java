package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.StatusPraesensData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class ComplaintsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public ComplaintsPanel(String id) {
		super(id, "title");
		
		ComplaintsForm form = new ComplaintsForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	private class ComplaintsForm extends SavingForm {
		private static final long serialVersionUID = 5280010870511738205L;

		public ComplaintsForm(String id) {
			super(id);
			
			StatusPraesensData data = StatusPraesensData.instance();
			
			// Complaints
			add(new TextField<String>("complaintsText", new PropertyModel<String>(data, "complaintsText")));
		}
	}
}
