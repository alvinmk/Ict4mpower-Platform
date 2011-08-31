package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskList {
 private List<Task> tasks = new ArrayList<Task>();
 
 public TaskList(){
 }
 
 public boolean addTask(Task t){
	 tasks.add(t);
	 return true;
 }
 
 public void removeTask(Task t){
	 tasks.remove(t);
 }
 
 public void removeTaskByIndex(int index){
	 tasks.remove(index); 
 }
 
 public List<HashMap> getNames(){
	 List<HashMap> l = new ArrayList<HashMap>();
	 for(Task  t : tasks ){
		 HashMap<String, Object> h = new HashMap<String,Object>();
		 h.put("name", t.getName());
		 h.put("index", tasks.indexOf(t));
		 l.add(h);
	 }
	 return l;
 }
 
 public Task getTaskByNumber(int index){
	 return tasks.get(index);
 }
  
 public List<Task> getTasks(){
	 return tasks;
 }
 
 
 
}
