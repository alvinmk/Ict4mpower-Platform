package ict4mpower.childHealth;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.Model;

public class SummaryCheck extends CheckBox {
	private static final long serialVersionUID = 6041474596586222030L;

	public SummaryCheck(String id, Object data) {
		super(id, new Model<Boolean>(data != null));
		if(data == null) setEnabled(false);
	}
}
