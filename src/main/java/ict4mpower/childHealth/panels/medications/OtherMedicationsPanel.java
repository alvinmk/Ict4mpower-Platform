package ict4mpower.childHealth.panels.medications;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for other (general) medications
 * (not vitamin A, de-worming or anti-malarial)
 * @author Joakim Lindskog
 *
 */
public class OtherMedicationsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	private ListView<Medicine> list;
	private GiveOtherMedicationPanel medPanel;

	/**
	 * Constructor
	 * @param id component id
	 */
	public OtherMedicationsPanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		add(new OtherMedicationsForm("form"));
	}
	
	/**
	 * Form for other medications panel
	 * @author Joakim Lindskog
	 *
	 */
	class OtherMedicationsForm extends SavingForm {
		private static final long serialVersionUID = -6533255463323812596L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public OtherMedicationsForm(String id) {
			super(id);
			
			MedicationsData data = MedicationsData.instance();
			if(data.getOtherMeds() == null) {
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
						if(med.getOtherMeds() != null && med.getDate().after(max)) {
							data.setOtherMeds(med.getOtherMeds());
							max = med.getDate();
						}
					}
				}
			}
			
			// Add table items
			list = new ListView<Medicine>("otherMeds", new PropertyModel<List<Medicine>>(data, "otherMeds")) {
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void populateItem(ListItem<Medicine> item) {
					Medicine vita = item.getModelObject();
					item.add(new OtherMedicationsRowPanel("rowPanel", vita));
				}
			};
			list.setOutputMarkupId(true);
			add(list);
			
			medPanel = new GiveOtherMedicationPanel("giveMedicationPanel", this, OtherMedicationsPanel.this);
			
			// Add 'give medication' button
			add(new AjaxLink<Object>("giveMedication") {
				private static final long serialVersionUID = 1L;
	
				@Override
				public void onClick(AjaxRequestTarget target) {
					medPanel.getDrugName().setDefaultModelObject(null);
					medPanel.getForm().setDefaultModelObject(null);
					medPanel.getReason().setDefaultModelObject(null);
					medPanel.getDose().setDefaultModelObject(null);
					medPanel.getInstructions().setDefaultModelObject(null);
					medPanel.setVisible(true);
					target.add(medPanel);
				}
			}, false);
			
			// Add 'give medication' component
			medPanel.setOutputMarkupPlaceholderTag(true);
			medPanel.setVisible(false);
			add(medPanel, false);
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			List<Medicine> l = list.getModelObject();
			
			l.add(new Medicine(medPanel.getDrugName().getConvertedInput(),
					medPanel.getForm().getConvertedInput(),
					medPanel.getDose().getConvertedInput(),
					medPanel.getReason().getConvertedInput(),
					medPanel.getInstructions().getConvertedInput(),
					new Date()));
			
			medPanel.setVisible(false);
		}
	}
}