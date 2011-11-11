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
 * Panel for check-up
 * @author Joakim Lindskog
 *
 */
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

	/**
	 * Constructor
	 * @param id component id
	 */
	public CheckUpPanel(String id) {
		super(id, "title");
		
		CheckUpForm form = new CheckUpForm("form"); 
		add(form);
		
		setForm(form, this);
	}
	
	/**
	 * Form for check-up panel
	 * @author Joakim Lindskog
	 *
	 */
	private class CheckUpForm extends SavingForm {
		private static final long serialVersionUID = 6330056812131097169L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public CheckUpForm(String id) {
			super(id);
			
			StatusPraesensData data = StatusPraesensData.instance();
			if(data.getCheckUp() == null) {
				data.setCheckUp(CHECK_UP);
			}
			
			list = new ListView<CheckInfoData>("checkUp", new PropertyModel<List<CheckInfoData>>(data, "checkUp")) {
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
