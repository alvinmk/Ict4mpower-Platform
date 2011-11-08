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

public class AntimalarialPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;

	@SuppressWarnings("unchecked")
	public AntimalarialPanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		MedicationsData data = MedicationsData.instance();
		if(data.getAntimalarial() == null) {
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
					if(med.getAntimalarial() != null && med.getDate().after(max)) {
						data.setAntimalarial(med.getAntimalarial());
						max = med.getDate();
					}
				}
			}
		}
		if(data.getAntimalarial() == null) {
			// Get standard medications from db
			PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
			List<Medicine> std = null;
			List<Medicine> meds = new ArrayList<Medicine>();
			Set<Object> medSet = ApplicationSocketTemp.getApplicationSocketTemp().getData("ChildHealth", "StandardAntimalarial");
			for(Object o : medSet) {
				// Get only first object - there should be only one
				std = (List<Medicine>) o;
				break;
			}
			for(Medicine med : std) {
				meds.add(new Medicine(pi, med.getName(), med.getCalField(), med.getCalAdd(), null,
						med.getDosage(), med.getBatchNr()));
			}
			data.setAntimalarial(meds);
		}
		
		add(new AntimalarialForm("form"));
	}
	
	class AntimalarialForm extends MedicineSavingForm {
		private static final long serialVersionUID = 2648446174418300654L;

		public AntimalarialForm(String id) {
			super(id);
			
			MedicationsData data = MedicationsData.instance();
			
			// Add table items
			list = new ListView<Medicine>("antimalarial", new PropertyModel<List<Medicine>>(data, "antimalarial")) {
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void populateItem(ListItem<Medicine> item) {
					item.add(new MedicationsPanel("rowPanel", item.getModel(), list, AntimalarialForm.this, medPanel));
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
					"antimalarial1",
					"antimalarial2"}));
			
			List<String> dosages = new ArrayList<String>(Arrays.asList(new String[] {
					"100 000 IU",
					"200 000 IU"}));
			
			medPanel = new GiveMedicationPanel("giveMedicationPanel", ages, medications, dosages,
					this, AntimalarialPanel.this);
			
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