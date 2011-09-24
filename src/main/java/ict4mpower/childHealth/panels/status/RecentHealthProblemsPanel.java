package ict4mpower.childHealth.panels.status;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import ict4mpower.childHealth.interfaces.ISavable;
import ict4mpower.childHealth.panels.CheckInfoPanel;
import ict4mpower.childHealth.panels.DivisionPanel;

public class RecentHealthProblemsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private final List<StringResourceModel> RECENT_PROBS = Arrays.asList(new StringResourceModel[] {
		new StringResourceModel("bact_infect", this, null),
		new StringResourceModel("accident", this, null),
		new StringResourceModel("hospitalization", this, null),
		new StringResourceModel("skin_diseases", this, null),
		new StringResourceModel("asthma", this, null),
		new StringResourceModel("allergy", this, null),
		new StringResourceModel("sleep_dist", this, null),
		new StringResourceModel("behaviour", this, null),
		new StringResourceModel("malaria", this, null),
		new StringResourceModel("hiv_aids", this, null),
		new StringResourceModel("other", this, null)
	});
	
	private ListView<StringResourceModel> list;

	public RecentHealthProblemsPanel(String id) {
		super(id, "title");

		list = new ListView<StringResourceModel>("recent_checks", RECENT_PROBS) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<StringResourceModel> item) {
				item.add(new CheckInfoPanel("rowPanel", item.getModelObject().getObject()));
			}
		};
		add(list);
	}
	
	public boolean save() {
		list.visitChildren(new IVisitor<Component, Object>() {
			public void component(Component c, IVisit<Object> o) {
				if(c instanceof ISavable) {
					((ISavable)c).save();
				}
			}
		});
		return true;
	}
}
