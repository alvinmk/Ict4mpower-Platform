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
package ict4mpower.childHealth.panels.growth;

import java.io.Serializable;

/**
 * Data class to store age data
 * @author Joakim Lindskog
 *
 */
public class AgeData implements Serializable {
	private static final long serialVersionUID = 8881353048567347442L;

	private int weeksMonths;
	private int extraDays;
	
	/**
	 * Constructor
	 * @param weeksMonths number of weeks/months
	 * @param extraDays number of extra days (if not even weeks/months)
	 */
	public AgeData(int weeksMonths, int extraDays) {
		this.setWeeksMonths(weeksMonths);
		this.setExtraDays(extraDays);
	}

	public int getWeeksMonths() {
		return weeksMonths;
	}

	public void setWeeksMonths(int weeksMonths) {
		this.weeksMonths = weeksMonths;
	}

	public int getExtraDays() {
		return extraDays;
	}

	public void setExtraDays(int extraDays) {
		this.extraDays = extraDays;
	}
}
