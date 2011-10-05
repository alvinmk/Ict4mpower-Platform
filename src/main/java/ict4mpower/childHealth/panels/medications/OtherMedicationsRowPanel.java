package ict4mpower.childHealth.panels.medications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class OtherMedicationsRowPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public OtherMedicationsRowPanel(String id, Medicine medicine) {
		super(id);
		
		// Add supplement values
		Label drugLabel = new Label("row_name", new Model<String>(medicine.getName()));
		add(drugLabel);
		Label formLabel = new Label("row_form", new Model<String>(medicine.getForm()));
		add(formLabel);
		Label doseLabel = new Label("row_dose", new Model<String>(medicine.getDose()));
		add(doseLabel);
		Label reasonsLabel = new Label("row_reason", new Model<String>(medicine.getReason()));
		add(reasonsLabel);
		Label instructionsLabel = new Label("row_instructions", new Model<String>(medicine.getInstructions()));
		add(instructionsLabel);
		Label dateLabel = new Label("row_dateGiven", new Model<String>(df.format(medicine.getGivenDate())));
		add(dateLabel);
	}
}
