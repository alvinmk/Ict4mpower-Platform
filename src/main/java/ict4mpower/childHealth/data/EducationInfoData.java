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
import java.util.List;

/**
 * Data class for storing Education info for one part
 * @author Joakim Lindskog
 *
 */
public class EducationInfoData implements Serializable {
	private static final long serialVersionUID = -5329457188033766021L;
	
	private String header;
	private String text;
	private List<CheckableOption> options;

	public EducationInfoData(String header, String text, List<CheckableOption> options) {
		this.setHeader(header);
		this.setText(text);
		this.setOptions(options);
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<CheckableOption> getOptions() {
		return options;
	}

	public void setOptions(List<CheckableOption> options) {
		this.options = options;
	}
}
