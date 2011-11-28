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
 * Panel for complaints
 * @author Joakim Lindskog
 *
 */
public class ComplaintsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public ComplaintsPanel(String id) {
		super(id, "title");
		
		ComplaintsForm form = new ComplaintsForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	/**
	 * Form for complaints panel
	 * @author Joakim Lindskog
	 *
	 */
	private class ComplaintsForm extends SavingForm {
		private static final long serialVersionUID = 5280010870511738205L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public ComplaintsForm(String id) {
			super(id);
			
			StatusPraesensData data = StatusPraesensData.instance();
			
			// Complaints
			add(new TextField<String>("complaintsText", new PropertyModel<String>(data, "complaintsText")));
		}
	}
}
