package ict4mpower.childHealth.panels.status;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.panels.CheckInfoData;
import ict4mpower.childHealth.panels.CheckInfoPanel;
import ict4mpower.childHealth.panels.DivisionPanel;

public class CheckUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private final List<CheckInfoData> CHECK_UP = Arrays.asList(new CheckInfoData[] {
		new CheckInfoData(new StringResourceModel("general", this, null)),
		new CheckInfoData(new StringResourceModel("skin", this, null)),
		new CheckInfoData(new StringResourceModel("spontan_motor", this, null)),
		new CheckInfoData(new StringResourceModel("gross_motor", this, null)),
		new CheckInfoData(new StringResourceModel("fine_motor", this, null)),
		new CheckInfoData(new StringResourceModel("tonus", this, null)),
		new CheckInfoData(new StringResourceModel("respiratory", this, null)),
		new CheckInfoData(new StringResourceModel("heart", this, null)),
		new CheckInfoData(new StringResourceModel("femoral", this, null)),
		new CheckInfoData(new StringResourceModel("abdomen", this, null)),
		new CheckInfoData(new StringResourceModel("genitalia", this, null)),
		new CheckInfoData(new StringResourceModel("hips", this, null)),
		new CheckInfoData(new StringResourceModel("skull", this, null)),
		new CheckInfoData(new StringResourceModel("spinal_cord", this, null)),
		new CheckInfoData(new StringResourceModel("extremities", this, null)),
		new CheckInfoData(new StringResourceModel("eyes", this, null)),
		new CheckInfoData(new StringResourceModel("ears", this, null)),
		new CheckInfoData(new StringResourceModel("ent", this, null)),
		new CheckInfoData(new StringResourceModel("teeth", this, null)),
		new CheckInfoData(new StringResourceModel("other", this, null)),
	});
	
	private ListView<CheckInfoData> list;

	public CheckUpPanel(String id) {
		super(id, "title");
		
		CheckUpForm form = new CheckUpForm("form"); 
		add(form);
		
		setForm(form, this);
	}
	
	private class CheckUpForm extends SavingForm {
		private static final long serialVersionUID = 6330056812131097169L;

		public CheckUpForm(String id) {
			super(id, CheckUpForm.class.getName());
			
			list = new ListView<CheckInfoData>("check_up", CHECK_UP) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<CheckInfoData> item) {
					item.add(new CheckInfoPanel("rowPanel", item.getModel()));
				}
			};
			list.setReuseItems(true);
			add(list);
		}
	}
}
