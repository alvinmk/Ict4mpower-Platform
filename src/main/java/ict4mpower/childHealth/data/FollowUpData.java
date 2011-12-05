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

import ict4mpower.childHealth.ChildHealthData;

import java.util.Date;

import models.BaseModel;

/**
 * Data class for the FollowUp task
 * @author Joakim Lindskog
 *
 */
public class FollowUpData extends BaseModel implements ChildHealthData {
	private static final long serialVersionUID = -8893036385045807296L;
	
	private static FollowUpData instance = null;

	public static FollowUpData instance() {
		if(instance == null) {
			instance = new FollowUpData();
		}
		return instance;
	}
	
	/** Date of follow-up visit */
	private Date date;
	private String message;
	
	private FollowUpData() {}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void reset() {
		setDate(null);
		setMessage(null);
	}
	
	public FollowUpData clone() {
		FollowUpData data = new FollowUpData();
		data.setDate(date);
		data.setMessage(message);
		return data;
	}
}
