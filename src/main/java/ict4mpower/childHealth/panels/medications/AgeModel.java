package ict4mpower.childHealth.panels.medications;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class AgeModel implements IModel<String> {
	private static final long serialVersionUID = 5130177704571243374L;

	private IModel<Medicine> medicine;
	private Component parent;
	
	public AgeModel(IModel<Medicine> medicine, Component parent) {
		this.medicine = medicine;
		this.parent = parent;
	}

	public void detach() {
		medicine.detach();
	}

	public String getObject() {
		return medicine.getObject().getDueAge(parent);
	}

	public void setObject(String object) {
		// Unused
	}
}
