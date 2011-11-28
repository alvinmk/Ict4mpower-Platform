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
package ict4mpower.childHealth.data;

import java.io.Serializable;

/**
 * Data object for checkable information in the Education task
 * @author Joakim Lindskog
 *
 */
public class CheckableOption implements Serializable, Cloneable {
	private static final long serialVersionUID = -8760200310456314580L;

	private String option;
	private boolean checked;
	
	/**
	 * Constructor
	 * @param option information text
	 */
	public CheckableOption(String option) {
		this.setOption(option);
		this.checked = false;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public CheckableOption clone() {
		CheckableOption o = new CheckableOption(option);
		o.setChecked(checked);
		return o;
	}
}
