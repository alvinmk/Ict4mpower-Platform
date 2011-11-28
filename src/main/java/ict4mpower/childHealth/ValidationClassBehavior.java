package ict4mpower.childHealth;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * A behaviour to visually indicate validation errors
 * @author Joakim Lindskog
 *
 */
public class ValidationClassBehavior extends Behavior {
	private static final long serialVersionUID = 4562221021482306992L;
	
	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		FormComponent<?> fc = (FormComponent<?>)component;
		if (!fc.isValid()) {
			tag.put("class", "error");
		}
		super.onComponentTag(component, tag);
	}
}