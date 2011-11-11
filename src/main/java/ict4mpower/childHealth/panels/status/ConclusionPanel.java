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
