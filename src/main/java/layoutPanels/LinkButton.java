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
package layoutPanels;

import org.apache.wicket.markup.html.panel.Panel;

/*
 * A Linkbutton contains a name that is the name of the task and parameter that is used as an parmeter to the request
 * 
 */
public class LinkButton{
	String name; 
	String parameter;
	Panel content;
	
	public LinkButton(String name, String parameter){
		this.name = name;
		this.parameter = parameter;
	}
}
