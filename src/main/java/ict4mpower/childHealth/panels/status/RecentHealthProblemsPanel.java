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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.CheckInfoData;
import ict4mpower.childHealth.data.StatusPraesensData;
import ict4mpower.childHealth.panels.CheckInfoPanel;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for recent health problems
 * @author Joakim Lindskog
 *
 */
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

	/**
	 * Constructor
	 * @param id component id
	 */
	public RecentHealthProblemsPanel(String id) {
		super(id, "title");

		RecentHealthProblemsForm form = new RecentHealthProblemsForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	/**
	 * Form for recent health problems panel
	 * @author Joakim Lindskog
	 *
	 */
	private class RecentHealthProblemsForm extends SavingForm {
		private static final long serialVersionUID = -5639227795730149964L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public RecentHealthProblemsForm(String id) {
			super(id);
			
			StatusPraesensData data = StatusPraesensData.instance();
			if(data.getRecentHealthProblems() == null) {
				data.setRecentHealthProblems(RECENT_PROBS);
			}
			
			list = new ListView<CheckInfoData>("recentHealthProblems", new PropertyModel<List<CheckInfoData>>(data, "recentHealthProblems")) {
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
