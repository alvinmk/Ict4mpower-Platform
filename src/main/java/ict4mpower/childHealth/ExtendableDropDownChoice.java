package ict4mpower.childHealth;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class ExtendableDropDownChoice<T> extends DropDownChoice<T> {
	private static final long serialVersionUID = -2583749507348856889L;
	
	private List<T> choicesList;

	public ExtendableDropDownChoice(String id, IModel<T> model,
			List<T> choices) {
		super(id, model, choices);
		choicesList = choices;
	}
	
	public ExtendableDropDownChoice(String id,
			IModel<T> model, List<T> choices,
			IChoiceRenderer<T> renderer) {
		super(id, model, choices, renderer);
		choicesList = choices;
	}

	@Override
	protected boolean localizeDisplayValues() {
		return true;
	}
	
	@Override
	public String getModelValue() {
		final T object = getModelObject();
		if (object != null) {
			int index = getChoices().indexOf(object);
			// Add missing choice
			if(index == -1) {
				index = choicesList.size()-1;
				choicesList.add(index, object);
			}
			return getChoiceRenderer().getIdValue(object, index);
		}
		else {
			return "";
		}
	}
}
