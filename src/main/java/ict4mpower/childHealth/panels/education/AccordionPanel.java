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

public class AccordionPanel extends DivisionPanel {
	private static final long serialVersionUID = 8587720392401248160L;

	public AccordionPanel(String id, String titleId, Accordion acc, String propertyName) {
		super(id, titleId);
		
		// Accordion
		add(acc);
		
		acc.add(new AccordionForm("form", propertyName));
	}
	
	class AccordionForm extends SavingForm {
		private static final long serialVersionUID = -4524436307145960370L;

		public AccordionForm(String id, String propertyName) {
			super(id);
			
			setForm(this, AccordionPanel.this);
			
			EducationData data = EducationData.instance();
			
			// Contents
			add(new ListView<EducationInfoData>("container",
					new PropertyModel<List<EducationInfoData>>(data, propertyName)) {
				private static final long serialVersionUID = 4806869925646123546L;

				@Override
				protected void populateItem(
						ListItem<EducationInfoData> item) {
					item.add(new AccordionContentsPanel("contents", item.getModel(), AccordionPanel.this));
				}
			}, propertyName);
		}
	}
}

class AccordionContentsPanel extends Panel {
	private static final long serialVersionUID = 2026992424299296925L;

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