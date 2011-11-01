package tasks;

/*
 * Copyright 2011 Elias.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 *
 * @author Elias
 */
public class Goals implements Serializable {
	private static final long serialVersionUID = -1942133361406207799L;
	
	//    private LinkedList goalList;
    private String name;
    private Map <String, TaskList> map = new HashMap<String, TaskList> ();
    final static Logger log = Logger.getLogger(Goals.class);
    
    public Goals(){
       
	}
    
    public void addGoal(String goal_name){
        map.put(goal_name,new TaskList()); 
    }
    
    public void removeGoal(String goal_name){
        map.remove(goal_name);
    }
    
    public boolean addTask(String goal_name, String task){
       return map.get(goal_name).addTask(task);
    }
    
    public TaskList getTasks(String goal_name){
        return map.get(goal_name);
    }
    
    public Set<String> getGoals(){
        return  map.keySet();
    }
}