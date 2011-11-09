package ict4mpower.childHealth;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.Model;

/**
 * Checkbox to use for the layout in VisitSummary
 * @author Joakim Lindskog
 *
 */
public class SummaryCheck extends CheckBox {
	private static final long serialVersionUID = 6041474596586222030L;

	/**
	 * Constructor
	 * @param id component id
	 * @param data the data for this component
	 */
	public SummaryCheck(String id, Object data) {
		super(id, new Model<Boolean>(data != null));
		if(data == null) setEnabled(false);
	}
}
