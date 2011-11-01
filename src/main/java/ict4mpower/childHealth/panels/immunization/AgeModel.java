package ict4mpower.childHealth.panels.immunization;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class AgeModel implements IModel<String> {
	private static final long serialVersionUID = 5130177704571243374L;

	private IModel<Vaccination> vaccination;
	private Component parent;
	
	public AgeModel(IModel<Vaccination> vaccination, Component parent) {
		this.vaccination = vaccination;
		this.parent = parent;
	}

	public void detach() {
		vaccination.detach();
	}

	public String getObject() {
		return vaccination.getObject().getDueAge(parent);
	}

	public void setObject(String object) {
		// Unused
	}
}
