package ict4mpower;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/*
 * What should be stored in the session
 */
public class AppSession extends WebSession{
	private static final long serialVersionUID = 1L;
	private String userID; //An Id for the current user
	private String goal; //Current goal
	private String task; //Current task
	private String[] roles;
	
	public AppSession(Request r){
		super(r);
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String[] getRoles() {
		return roles;
	}
	
}
