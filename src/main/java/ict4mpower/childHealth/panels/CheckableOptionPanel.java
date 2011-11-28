package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.data.CheckableOption;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A panel for the Education task with a checkbox and a label
 * @author Joakim Lindskog
 *
 */
public class CheckableOptionPanel extends Panel {
	private static final long serialVersionUID = 8243634455154294405L;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param parent parent component
	 */
	public CheckableOptionPanel(String id, IModel<CheckableOption> model, Component parent) {
		super(id);
		// Add check
		add(new CheckBox("check", new PropertyModel<Boolean>(model, "checked")));
		
		// Add text
		add(new Label("option", parent.getString(model.getObject().getOption())));
	}
}