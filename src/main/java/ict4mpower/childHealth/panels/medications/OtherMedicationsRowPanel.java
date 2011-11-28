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
package ict4mpower.childHealth.panels.medications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Row panel for the other medications table
 * @author Joakim Lindskog
 *
 */
public class OtherMedicationsRowPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Constructor
	 * @param id component id
	 * @param medicine the medicine
	 */
	public OtherMedicationsRowPanel(String id, Medicine medicine) {
		super(id);
		
		// Add supplement values
		Label drugLabel = new Label("row_name", new Model<String>(medicine.getName()));
		add(drugLabel);
		Label formLabel = new Label("row_form", new Model<String>(medicine.getForm()));
		add(formLabel);
		Label doseLabel = new Label("row_dose", new Model<String>(medicine.getDosage()));
		add(doseLabel);
		Label reasonsLabel = new Label("row_reason", new Model<String>(medicine.getReason()));
		add(reasonsLabel);
		Label instructionsLabel = new Label("row_instructions", new Model<String>(medicine.getInstructions()));
		add(instructionsLabel);
		Label dateLabel = new Label("row_dateGiven", new Model<String>(df.format(medicine.getGivenDate())));
		add(dateLabel);
	}
}
