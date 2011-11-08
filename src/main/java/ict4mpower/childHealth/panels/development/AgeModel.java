package ict4mpower.childHealth.panels.development;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class AgeModel implements IModel<String> {
	private static final long serialVersionUID = 5130177704571243374L;

	private IModel<MilestoneTests> tests;
	private Component parent;
	
	public AgeModel(IModel<MilestoneTests> tests, Component parent) {
		this.tests = tests;
		this.parent = parent;
	}

	public void detach() {
		tests.detach();
	}

	public String getObject() {
		return tests.getObject().getDueAge(parent);
	}

	public void setObject(String object) {
		// Unused
	}
}
