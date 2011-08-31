package tasks;

import org.apache.wicket.markup.html.panel.Panel;

public abstract class Task extends Panel {
	private static final long serialVersionUID = 2400847116292879259L;
	private String name;
 
 public Task(String name){
	 super("task");
	 this.name = name;
 }
 
 public String getName(){
	 return name;
 }
}
