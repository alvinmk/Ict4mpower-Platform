package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskList {
 private List<Tab> tasks = new ArrayList<Tab>();
 private String goal;
 
 public TaskList(String goal){
	 this.goal = goal;
 }
 
 public void addTask(Tab t){
	 tasks.add(t);
 }
 
 public void removeTask(Tab t){
	 tasks.remove(t);
 }
 
 public void removeTaskByIndex(int index){
	 tasks.remove(index); 
 }
 
 public List<HashMap> getNames(){
	 List<HashMap> l = new ArrayList<HashMap>();
	 for(Tab  t : tasks ){
		 HashMap h = new HashMap();
		 h.put("name", t.getName());
		 h.put("index", tasks.indexOf(t));
		 l.add(h);
	 }
	 return l;
 }
 
 public Tab getTaskByNumber(int index){
	 return tasks.get(index);
 }
  
 public List<Tab> getTasks(){
	 return tasks;
 }
 
 
 
}
