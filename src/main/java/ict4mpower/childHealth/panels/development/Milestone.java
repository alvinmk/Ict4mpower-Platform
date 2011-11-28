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
package ict4mpower.childHealth.panels.development;

import java.io.Serializable;
import java.util.Date;

/**
 * A milestone
 * @author Joakim Lindskog
 *
 */
public class Milestone implements Serializable {
	private static final long serialVersionUID = -9155283621010002742L;
	
	public MilestoneTests tests;
	public Date date;
	public short grossMotor;
	public short fineMotor;
	public short communication;
	public short cognitive;
	public Short[] hearing;
	public Short[] eyesight;
	
	/**
	 * Constructor
	 * @param tests the milestone tests to use
	 * @param measuredDate the date this milestone was measured
	 * @param grossMotor value for gross motor skills
	 * @param fineMotor value for fine motor skills
	 * @param communication value for communication skills
	 * @param cognitive value for cognitive abilities
	 * @param hearing values for hearing
	 * @param eyesight values for eyesight
	 */
	public Milestone(MilestoneTests tests, Date measuredDate, short grossMotor, short fineMotor,
			short communication, short cognitive, Short[] hearing, Short[] eyesight) {
		this.tests = tests;
		this.date = measuredDate;
		this.grossMotor = grossMotor;
		this.fineMotor = fineMotor;
		this.communication = communication;
		this.cognitive = cognitive;
		this.hearing = hearing;
		this.eyesight = eyesight;
	}
}
