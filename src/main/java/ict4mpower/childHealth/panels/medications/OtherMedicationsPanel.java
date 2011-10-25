package ict4mpower.childHealth.panels.medications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class OtherMedicationsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");
	private ListView<Medicine> list;
	private GiveOtherMedicationPanel medPanel;

	public OtherMedicationsPanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		add(new OtherMedicationsForm("form"));
	}
	
	class OtherMedicationsForm extends SavingForm {
		private static final long serialVersionUID = -6533255463323812596L;

		//TODO Temporary
		public OtherMedicationsForm(String id) {
			super(id);

			List<Medicine> meds = null;
			try {
				meds = new ArrayList<Medicine>(Arrays.asList(new Medicine[]{
						new Medicine("Other medicine 1", "Pills", "100 000 IU", "Had some problem", "Take 1 each day for 30 days", df.parse("01/08/2011"), this),
						new Medicine("Cough syrup", "Syrup", "100 000 IU", "Had a cough", "One tablespoon 3 times a day", df.parse("01/09/2011"), this)
				}));
			} catch(Exception e) {
				//
			}
			
			MedicationsData data = MedicationsData.instance();
			// TODO Temporary
			if(data.getOtherMeds() == null) {
				data.setOtherMeds(meds);
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
					new Date(),
					this));
			
			medPanel.setVisible(false);
		}
	}
}