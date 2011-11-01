package ict4mpower.childHealth.panels.growth;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class AgeModel implements IModel<String> {
	private static final long serialVersionUID = 5130177704571243374L;

	private IModel<Indicator> indicator;
	private Component parent;
	
	public AgeModel(IModel<Indicator> indicator, Component parent) {
		this.indicator = indicator;
		this.parent = parent;
	}

	public void detach() {
		indicator.detach();
	}

	public String getObject() {
		return indicator.getObject().getAge(parent);
	}

	public void setObject(String object) {
		// Unused
	}
}
