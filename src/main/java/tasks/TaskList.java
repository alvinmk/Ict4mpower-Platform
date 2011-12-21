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
package tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TaskList implements Serializable {
	static final Logger log = Logger.getLogger(TaskList.class);
	private static final long serialVersionUID = 867500089746391660L;
private List<String> tasks = new ArrayList<String>();
 
 public TaskList(){}
 
 public boolean addTask(String task){
	tasks.add(task);
	return true;
 }
 
 public void removeTask(String t){
	 tasks.remove(t);
 }
 
 public void removeTaskByIndex(int index){
	 tasks.remove(index); 
 }
 
 public List<HashMap<String, Integer>> getTaskNamesAndIndexes(){
	 List<HashMap<String, Integer>> l = new ArrayList <HashMap<String, Integer>>();
	 for(String  task : tasks ){
		 HashMap<String, Integer> h = new HashMap<String, Integer>();
		 h.put(task, tasks.indexOf(task));
		 l.add(h);
	 }
	 return l;
 }
 
 public Task getTaskByNumber(int index){
	 return constructTask(tasks.get(index));
 }
  
 public List<String> getTaskNames(){
	 return tasks;
 }
 
 public Task getTaskPanel(String name){
	 for( String t : tasks){
		 if( t.equals(name)){
			 log.debug("Constructing task " +name);
			 return constructTask(name);
		 }
	 }
	 return null;	 
 }
 
 
 @SuppressWarnings({ "rawtypes", "unchecked" })
private Task constructTask(String name, String pack){
	 log.info("Trying to create task for " +pack +"." +name);
	 Task returnTask = null;
	 try {
			Class cl = Class.forName(pack+"."+name);
			java.lang.reflect.Constructor co = cl.getConstructor(new Class[]{String.class});
	        Object retobj = co.newInstance(new Object []{name});
	        returnTask = (Task) retobj;
		  }
		catch (Exception e) {
		  e.printStackTrace();
		} 
	return returnTask;
 }
 
private Task constructTask(String name){
	 return constructTask(name, "ict4mpower.childHealth.tasks");
 }
 
 
}
