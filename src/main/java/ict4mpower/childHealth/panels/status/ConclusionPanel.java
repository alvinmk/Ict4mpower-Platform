package ict4mpower.childHealth.panels.status;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.StatusPraesensData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for conclusions (diagnosis)
 * @author Joakim Lindskog
 *
 */
public class ConclusionPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public ConclusionPanel(String id) {
		super(id, "title");
		
		ConclusionForm form = new ConclusionForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	/**
	 * Form for conclusion panel
	 * @author Joakim Lindskog
	 *
	 */
	private class ConclusionForm extends SavingForm {
		private static final long serialVersionUID = 5280011230511738205L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public ConclusionForm(String id) {
			super(id);
			
			StatusPraesensData data = StatusPraesensData.instance();
			
			// Complaints
			add(new TextField<String>("conclusionText", new PropertyModel<String>(data, "conclusionText")));
		}
	}
}
