package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.medications.AntimalarialPanel;
import ict4mpower.childHealth.panels.medications.DeWormingPanel;
import ict4mpower.childHealth.panels.medications.OtherMedicationsPanel;
import ict4mpower.childHealth.panels.medications.VitaminASupplementationPanel;

import tasks.Task;

public class MedicationsTask extends Task {
	private static final long serialVersionUID = 7860744301857688767L;

	public MedicationsTask(String name) {
		super(name);
		
		// Vitamin A supplements
		add(new VitaminASupplementationPanel("vitaminA"));
		
		// De-worming treatment
		add(new DeWormingPanel("deworming"));
		
		// Antimalarial treatment
		add(new AntimalarialPanel("antimalarial"));
		
		// Other medications
		add(new OtherMedicationsPanel("otherMeds"));
	}
}
