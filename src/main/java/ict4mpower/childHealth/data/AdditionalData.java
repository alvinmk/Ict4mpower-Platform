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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.BaseModel;

import org.apache.wicket.Component;

/**
 * Data class for the AdditionalInfo task
 * @author Joakim Lindskog
 *
 */
public class AdditionalData extends BaseModel implements ChildHealthData {
	private static final long serialVersionUID = -283564779395916587L;
	
	private static AdditionalData instance;
	
	public static AdditionalData instance() {
		if(instance == null) {
			instance = new AdditionalData();
		}
		return instance;
	}

	private List<String> reasons;
	private Date date;
	
	private AdditionalData() {}
	
	public List<String> getReasons() {
		if(reasons == null) {
			reasons = new ArrayList<String>();
		}
		return reasons;
	}

	public void setReasons(List<String> reasons) {
		this.reasons = reasons;
	}
	
	public String getReasonsAsString(Component c) {
		String rString = "";
		for(String s : getReasons()) {
			rString += c.getString(s)+"\n";
		}
		return rString;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void reset() {
		setDate(null);
		setReasons(null);
	}
	
	public AdditionalData clone() {
		AdditionalData data = new AdditionalData();
		data.setDate(date);
		data.setReasons(reasons);
		return data;
	}
}
