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

public class CheckUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private final List<StringResourceModel> CHECK_UP = Arrays.asList(new StringResourceModel[] {
		new StringResourceModel("general", this, null),
		new StringResourceModel("skin", this, null),
		new StringResourceModel("spontan_motor", this, null),
		new StringResourceModel("gross_motor", this, null),
		new StringResourceModel("fine_motor", this, null),
		new StringResourceModel("tonus", this, null),
		new StringResourceModel("respiratory", this, null),
		new StringResourceModel("heart", this, null),
		new StringResourceModel("femoral", this, null),
		new StringResourceModel("abdomen", this, null),
		new StringResourceModel("genitalia", this, null),
		new StringResourceModel("hips", this, null),
		new StringResourceModel("skull", this, null),
		new StringResourceModel("spinal_cord", this, null),
		new StringResourceModel("extremities", this, null),
		new StringResourceModel("eyes", this, null),
		new StringResourceModel("ears", this, null),
		new StringResourceModel("ent", this, null),
		new StringResourceModel("teeth", this, null),
		new StringResourceModel("other", this, null),
	});
	
	private ListView<StringResourceModel> list;

	public CheckUpPanel(String id) {
		super(id, "title");

		list = new ListView<StringResourceModel>("check_up", CHECK_UP) {
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
