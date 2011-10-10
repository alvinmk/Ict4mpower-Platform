package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.SingleTextData;

public class ConclusionPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public ConclusionPanel(String id) {
		super(id, "title");
		
		ConclusionForm form = new ConclusionForm("form");
		add(form);
		
		setForm(form, this.getClass().getName()+"Frame");
	}
	
	private class ConclusionForm extends SavingForm {
		private static final long serialVersionUID = 5280011230511738205L;

		public ConclusionForm(String id) {
			super(id, ConclusionForm.class.getName());
			
			SingleTextData data = new SingleTextData();
			
			// Complaints
			add(new TextField<String>("conclusion_text", new PropertyModel<String>(data, "text")));
		}
	}
}
