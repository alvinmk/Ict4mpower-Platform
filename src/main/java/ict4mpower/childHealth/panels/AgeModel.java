package ict4mpower.childHealth.panels;


import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * An age model
 * @author Joakim Lindskog
 *
 */
public class AgeModel<T extends IDueAge> implements IModel<String> {
	private static final long serialVersionUID = 5130177704571243374L;

	private IModel<T> model;
	private Component parent;
	
	/**
	 * Constructor
	 * @param model inner model
	 * @param parent parent component
	 */
	public AgeModel(IModel<T> model, Component parent) {
		this.model = model;
		this.parent = parent;
	}

	public void detach() {
		model.detach();
	}

	public String getObject() {
		return model.getObject().getDueAge(parent);
	}

	public void setObject(String object) {
		// Unused
	}
}
