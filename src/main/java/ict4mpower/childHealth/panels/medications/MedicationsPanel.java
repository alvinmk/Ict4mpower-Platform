package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.panels.StatusDuePanel;
import ict4mpower.childHealth.panels.StatusMissedPanel;
import ict4mpower.childHealth.panels.StatusPanel;
import ict4mpower.childHealth.panels.StatusTakenPanel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class MedicationsPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public MedicationsPanel(String id, Medicine medicine) {
		super(id);
		
		// Add supplement values
		Label ageLabel = new Label("age", new Model<String>(medicine.getAge()));
		add(ageLabel);
		Label drugLabel = new Label("name", new Model<String>(medicine.getName()));
		add(drugLabel);
		Label doseLabel = new Label("dose", new Model<String>(medicine.getDose()));
		add(doseLabel);
		StatusPanel statusPanel = null;
		String status = medicine.getStatus();
		if(status.contentEquals("missed")) {
			statusPanel = new StatusMissedPanel("status");
		}
		else if(status.contentEquals("taken")) {
			statusPanel = new StatusTakenPanel("status");
		}
		else {
			statusPanel = new StatusDuePanel("status", medicine.getDueDate());
		}
		add(statusPanel);
		Label dateLabel = null;
		if(medicine.getGivenDate() != null) {
			dateLabel = new Label("dateGiven", new Model<String>(df.format(medicine.getGivenDate())));
		}
		else {
			dateLabel = new Label("dateGiven", "");
		}
		add(dateLabel);
	}
}
