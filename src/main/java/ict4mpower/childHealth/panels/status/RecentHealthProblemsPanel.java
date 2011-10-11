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

public class RecentHealthProblemsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private final List<CheckInfoData> RECENT_PROBS = Arrays.asList(new CheckInfoData[] {
		new CheckInfoData(new StringResourceModel("bact_infect", this, null)),
		new CheckInfoData(new StringResourceModel("accident", this, null)),
		new CheckInfoData(new StringResourceModel("hospitalization", this, null)),
		new CheckInfoData(new StringResourceModel("skin_diseases", this, null)),
		new CheckInfoData(new StringResourceModel("asthma", this, null)),
		new CheckInfoData(new StringResourceModel("allergy", this, null)),
		new CheckInfoData(new StringResourceModel("sleep_dist", this, null)),
		new CheckInfoData(new StringResourceModel("behaviour", this, null)),
		new CheckInfoData(new StringResourceModel("malaria", this, null)),
		new CheckInfoData(new StringResourceModel("hiv_aids", this, null)),
		new CheckInfoData(new StringResourceModel("other", this, null))
	});
	
	private ListView<CheckInfoData> list;

	public RecentHealthProblemsPanel(String id) {
		super(id, "title");

		RecentHealthProblemsForm form = new RecentHealthProblemsForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	private class RecentHealthProblemsForm extends SavingForm {
		private static final long serialVersionUID = -5639227795730149964L;

		public RecentHealthProblemsForm(String id) {
			super(id, RecentHealthProblemsForm.class.getName());
			
			list = new ListView<CheckInfoData>("recent_checks", RECENT_PROBS) {
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
