package ict4mpower.childHealth.panels.medications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class OtherMedicationsPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");

	public OtherMedicationsPanel(String id) {
		super(id, "title", false);
		
		//TODO Temporary
		List<Medicine> meds = null;
		try {
			meds = Arrays.asList(new Medicine[]{
					new Medicine("Other medicine 1", "Pills", "100 000 IU", "Had some problem", "Take 1 each day for 30 days", df.parse("01/08/2011"), this),
					new Medicine("Cough syrup", "Syrup", "100 000 IU", "Had a cough", "One tablespoon 3 times a day", df.parse("01/09/2011"), this)
			});
		} catch(Exception e) {
			//
		}
		
		MedicationsData data = MedicationsData.instance();
		// TODO Temporary
		if(data.getOtherMeds() == null) {
			data.setOtherMeds(meds);
		}
		
		// Add table items
		add(new ListView<Medicine>("otherMeds", new PropertyModel<List<Medicine>>(data, "otherMeds")) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Medicine> item) {
				Medicine vita = item.getModelObject();
				item.add(new OtherMedicationsRowPanel("rowPanel", vita));
			}
		});
		
		final GiveOtherMedicationPanel vPanel = new GiveOtherMedicationPanel("giveMedicationPanel");
		
		// Add 'give medication' button
		add(new AjaxLink<Object>("giveMedication") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				vPanel.setVisible(true);
				target.add(vPanel);
			}
		});
		
		// Add 'give medication' component
		vPanel.setOutputMarkupPlaceholderTag(true);
		vPanel.setVisible(false);
		add(vPanel);
	}
}