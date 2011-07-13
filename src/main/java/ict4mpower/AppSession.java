package ict4mpower;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class AppSession extends WebSession{
	private static final long serialVersionUID = 1L;
	private String userID;
	private String goal;
	private String task;
	
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
	
}
