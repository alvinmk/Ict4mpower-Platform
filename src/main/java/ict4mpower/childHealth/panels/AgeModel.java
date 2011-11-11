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
