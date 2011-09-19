package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import authentication.OpenIdCallbackPage;

public class TaskList {
 private List<String> tasks = new ArrayList<String>();
 
 public TaskList(){}
 static final Logger log = Logger.getLogger(OpenIdCallbackPage.class);
 
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
 
 public List<HashMap> getTaskNamesAndIndexes(){
	 List<HashMap> l = new ArrayList <HashMap>();
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
 
 
 private Task constructTask(String name, String pack){
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
	 return constructTask(name, "tasks");
 }
 
 
}
