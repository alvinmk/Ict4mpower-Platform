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