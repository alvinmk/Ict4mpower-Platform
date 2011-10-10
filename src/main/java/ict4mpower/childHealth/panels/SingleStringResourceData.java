package ict4mpower.childHealth.panels;

import java.io.Serializable;

import org.apache.wicket.model.StringResourceModel;

public class SingleStringResourceData implements Serializable {
	private static final long serialVersionUID = 6252834741048745658L;

	private StringResourceModel model;

	public void setModel(StringResourceModel model) {
		this.model = model;
	}

	public StringResourceModel getModel() {
		return model;
	}
}
