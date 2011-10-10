package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.SingleTextData;

public class ComplaintsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public ComplaintsPanel(String id) {
		super(id, "title");
		
		ComplaintsForm form = new ComplaintsForm("form");
		add(form);
		
		setForm(form, this.getClass().getName()+"Frame");
	}
	
	private class ComplaintsForm extends SavingForm {
		private static final long serialVersionUID = 5280010870511738205L;

		public ComplaintsForm(String id) {
			super(id, ComplaintsForm.class.getName());
			
			SingleTextData data = new SingleTextData();
			
			// Complaints
			add(new TextField<String>("complaints_text", new PropertyModel<String>(data, "text")));
		}
	}
}
