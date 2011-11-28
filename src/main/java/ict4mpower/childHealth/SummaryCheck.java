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
