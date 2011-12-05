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

import models.BaseModel;

import org.apache.wicket.model.StringResourceModel;

/**
 * Data for checkable and editable data in the StatusPraesens task
 * @author Joakim Lindskog
 *
 */
public class CheckInfoData extends BaseModel implements Serializable {
	private static final long serialVersionUID = 4232181354140299404L;
	
	/** Whether this item is selected or not */
	private boolean check;
	/** Info text accompanying this item */
	private String info;
	/** Text label to describe this item */
	private StringResourceModel label;
	
	/**
	 * Constructor
	 * @param label a model for the label text
	 */
	public CheckInfoData(StringResourceModel label) {
		this.label = label;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean isCheck() {
		return check;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	public void setLabel(StringResourceModel label) {
		this.label = label;
	}
	public String getLabel() {
		return label.getObject();
	}
}
