/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package ict4mpower.childHealth;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * A DropDownChoice that is extendable
 * @author Joakim Lindskog
 *
 * @param <T>
 */
public class ExtendableDropDownChoice<T> extends DropDownChoice<T> {
	private static final long serialVersionUID = -2583749507348856889L;
	
	private List<T> choicesList;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param choices the list of choices
	 */
	public ExtendableDropDownChoice(String id, IModel<T> model,
			List<T> choices) {
		super(id, model, choices);
		choicesList = choices;
	}
	
	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param choices the list of choices 
	 * @param renderer the IChoiceRenderer to use
	 */
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
