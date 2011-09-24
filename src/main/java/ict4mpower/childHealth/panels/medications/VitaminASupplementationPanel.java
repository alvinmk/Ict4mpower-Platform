package ict4mpower.childHealth.panels.medications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.panels.DivisionPanel;

public class VitaminASupplementationPanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");

	public VitaminASupplementationPanel(String id) {
		super(id, "title", false);
		
		//TODO Temporary
		List<Medicine> vitamins = null;
		try {
			vitamins = Arrays.asList(new Medicine[]{
					new Medicine("0", "Vitamin A", "100 000 IU", df.parse("01/08/2011"), df.parse("01/08/2011"), this),
					new Medicine("6m", "Vitamin A", "100 000 IU", df.parse("01/08/2011"), df.parse("01/08/2011"), this)
			});
		} catch(Exception e) {
			//
		}
		
		// Add table items
		add(new ListView<Medicine>("vitamins", vitamins) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Medicine> item) {
				Medicine vita = item.getModelObject();
				item.add(new MedicationsPanel("rowPanel", vita));
			}
		});
		
		// 'Give medication' panel
		List<StringResourceModel> ages = new ArrayList<StringResourceModel>(Arrays.asList(new StringResourceModel[] {
				new StringResourceModel("at_birth", this, null),
				new StringResourceModel("6w", this, null),
				new StringResourceModel("10w", this, null),
				new StringResourceModel("14w", this, null),
				new StringResourceModel("9m", this, null)}));
		
		List<StringResourceModel> medications = new ArrayList<StringResourceModel>(Arrays.asList(new StringResourceModel[] {
				new StringResourceModel("bcg", this, null),
				new StringResourceModel("oral_polio_0", this, null),
				new StringResourceModel("oral_polio_1", this, null),
				new StringResourceModel("oral_polio_2", this, null),
				new StringResourceModel("oral_polio_3", this, null),
				new StringResourceModel("dpt_hepb_hib_1", this, null),
				new StringResourceModel("dpt_hepb_hib_2", this, null),
				new StringResourceModel("dpt_hepb_hib_3", this, null),
				new StringResourceModel("measles", this, null)}));
		
		List<String> dosages = new ArrayList<String>(Arrays.asList(new String[] {
				"0.05 ml",
				"0.1 ml",
				"0.15 ml",
				"0.20 ml"}));
		
		final GiveMedicationPanel vPanel = new GiveMedicationPanel("giveMedicationPanel", ages, medications, dosages);
		
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