package ict4mpower.childHealth.panels.dash;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ict4mpower.childHealth.data.AdditionalData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class SpecialCarePanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	public SpecialCarePanel(String id) {
		super(id, "title", false);
		
		// Reasons text
		// Fetch info for special care reasons
		AdditionalData data = AdditionalData.instance();
		add(new ListView<String>("reasonsList", new Model<ArrayList<String>>((ArrayList<String>) data.getReasons())) {
			private static final long serialVersionUID = -3943623377097933936L;

			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new SpecialCareRowPanel("rowPanel", item.getModel(), SpecialCarePanel.this));
			}
		});
	}
}

class SpecialCareRowPanel extends Panel {
	private static final long serialVersionUID = -2836463365050103653L;

	public SpecialCareRowPanel(String id, IModel<String> model, Panel panel) {
		super(id);
		
		add(new Label("text", panel.getString(model.getObject())));
	}
}
