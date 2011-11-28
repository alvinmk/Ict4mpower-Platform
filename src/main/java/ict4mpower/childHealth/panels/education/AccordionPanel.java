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
package ict4mpower.childHealth.panels.education;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.accordion.Accordion;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.CheckableOption;
import ict4mpower.childHealth.data.EducationData;
import ict4mpower.childHealth.data.EducationInfoData;
import ict4mpower.childHealth.panels.CheckableOptionPanel;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * A panel to display the education info in an accordion
 * @author Joakim Lindskog
 *
 */
public class AccordionPanel extends DivisionPanel {
	private static final long serialVersionUID = 8587720392401248160L;

	/**
	 * Constructor
	 * @param id component id
	 * @param titleId the property key of the title
	 * @param acc the accordion
	 * @param propertyName the property key to use for the contents of the list view
	 * and as the component id of the saved list view
	 */
	public AccordionPanel(String id, String titleId, Accordion acc, String propertyName) {
		super(id, titleId);
		
		// Accordion
		add(acc);
		
		acc.add(new AccordionForm("form", propertyName));
	}
	
	/**
	 * The form for accordion panel
	 * @author Joakim Lindskog
	 *
	 */
	class AccordionForm extends SavingForm {
		private static final long serialVersionUID = -4524436307145960370L;

		/**
		 * Constructor
		 * @param id component id
		 * @param propertyName the property key to use for the contents of the list view
		 * and as the component id of the saved list view
		 */
		public AccordionForm(String id, String propertyName) {
			super(id);
			
			setForm(this, AccordionPanel.this);
			
			EducationData data = EducationData.instance();
			
			// Contents
			ListView<EducationInfoData> list = new ListView<EducationInfoData>("container",
					new PropertyModel<List<EducationInfoData>>(data, propertyName)) {
				private static final long serialVersionUID = 4806869925646123546L;

				@Override
				protected void populateItem(
						ListItem<EducationInfoData> item) {
					item.add(new AccordionContentsPanel("contents", item.getModel(), AccordionPanel.this));
				}
			};
			add(list, propertyName);
		}
	}
}

/**
 * A panel for the contents of the accordion
 * @author Joakim Lindskog
 *
 */
class AccordionContentsPanel extends Panel {
	private static final long serialVersionUID = 2026992424299296925L;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param parent parent component
	 */
	public AccordionContentsPanel(String id, IModel<EducationInfoData> model, Component parent) {
		super(id);
		// Add heading
		add(new Label("heading", parent.getString(model.getObject().getHeader())));
		// Add text
		add(new MultiLineLabel("text", parent.getString(model.getObject().getText())));
		// Add options
		ListView<CheckableOption> options = new ListView<CheckableOption>("options", new PropertyModel<List<CheckableOption>>(model, "options")) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<CheckableOption> item) {
				item.add(new CheckableOptionPanel("option", item.getModel(), this));
			}
		};
		add(options);
	}
}