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