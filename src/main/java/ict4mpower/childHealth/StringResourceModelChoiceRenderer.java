package ict4mpower.childHealth;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

public class StringResourceModelChoiceRenderer implements IChoiceRenderer<StringResourceModel> {
	private static final long serialVersionUID = 1L;

	public Object getDisplayValue(StringResourceModel model) {
		return model.getObject();
	}

	public String getIdValue(StringResourceModel model, int num) {
		return model.getObject();
	}
}
