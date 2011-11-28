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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import models.PatientInfo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import storage.ApplicationSocketTemp;
import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for de-worming medications
 * @author Joakim Lindskog
 *
 */
public class DeWormingPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;

	/**
	 * Constructor
	 * @param id component id
	 */
	@SuppressWarnings("unchecked")
	public DeWormingPanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		MedicationsData data = MedicationsData.instance();
		if(data.getDeworming() == null) {
			Date max = null;
			try {
				max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			MedicationsData med = null;
			// Get from db
			Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
			for(Object o : set) {
				if(o instanceof MedicationsData) {
					med = (MedicationsData) o;
					if(med.getDeworming() != null && med.getDate().after(max)) {
						data.setDeworming(med.getDeworming());
						max = med.getDate();
					}
				}
			}
		}
		if(data.getDeworming() == null) {
			// Get standard medications from db
			PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
			List<Medicine> std = null;
			List<Medicine> meds = new ArrayList<Medicine>();
			Set<Object> medSet = ApplicationSocketTemp.getApplicationSocketTemp().getData("ChildHealth", "StandardDeworming");
			for(Object o : medSet) {
				// Get only first object - there should be only one
				std = (List<Medicine>) o;
				break;
			}
			for(Medicine med : std) {
				meds.add(new Medicine(pi, med.getName(), med.getCalField(), med.getCalAdd(), null,
						med.getDosage(), med.getBatchNr()));
			}
			data.setDeworming(meds);
		}
		
		add(new DeWormingForm("form"));
	}
	
	/**
	 * Form for de-worming panel
	 * @author Joakim Lindskog
	 *
	 */
	class DeWormingForm extends MedicineSavingForm {
		private static final long serialVersionUID = 2648446174418300654L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public DeWormingForm(String id) {
			super(id);
			
			MedicationsData data = MedicationsData.instance();
			
			// Add table items
			list = new ListView<Medicine>("deworming", new PropertyModel<List<Medicine>>(data, "deworming")) {
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void populateItem(ListItem<Medicine> item) {
					item.add(new MedicationsPanel("rowPanel", item.getModel(), list, DeWormingForm.this, medPanel));
				}
			};
			list.setOutputMarkupId(true);
			add(list);
			
			// 'Give medication' panel
			List<Object[]> ages = new ArrayList<Object[]>(Arrays.asList(new Object[][] {
					new Object[]{"at_birth", null},
					new Object[]{"years", 0.5f},
					new Object[]{"years", 1f},
					new Object[]{"years", 1.5f},
					new Object[]{"years", 2f},
					new Object[]{"years", 2.5f},
					new Object[]{"years", 3f},
					new Object[]{"years", 3.5f},
					new Object[]{"years", 4f},
					new Object[]{"years", 4.5f},
					new Object[]{"years", 5f}}));
			
			List<String> medications = new ArrayList<String>(Arrays.asList(new String[] {
					"deworming1",
					"deworming2"}));
			
			List<String> dosages = new ArrayList<String>(Arrays.asList(new String[] {
					"100 000 IU",
					"200 000 IU"}));
			
			medPanel = new GiveMedicationPanel("giveMedicationPanel", ages, medications, dosages,
					this, DeWormingPanel.this);
			
			// Add 'give medication' button
			add(new AjaxLink<Object>("giveMedication") {
				private static final long serialVersionUID = 1L;
	
				@Override
				public void onClick(AjaxRequestTarget target) {
					medPanel.getAgeChoice().setDefaultModelObject(null);
					medPanel.getMedicineChoice().setDefaultModelObject(null);
					medPanel.getDosageChoice().setDefaultModelObject(null);
					medPanel.getSerialNr().setDefaultModelObject(null);
					medPanel.setVisible(true);
					target.add(medPanel);
				}
			}, false);
			
			// Add 'give medication' component
			medPanel.setOutputMarkupPlaceholderTag(true);
			medPanel.setVisible(false);
			add(medPanel, false);
		}
	}
}