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
