package ict4mpower;

import java.util.HashMap;
import java.util.List;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.ClientInfo;
import org.apache.wicket.request.Request;

import Models.PatientInfo;
/*
 * What should be stored in the session
 */
public class AppSession extends WebSession{
	private static final long serialVersionUID = 1L;
	private String userID; //An Id for the current user
	private String goal; //Current goal
	private String task; //Current task
	private String[] roles;
	private String currentVisit;
	private List<String> allVisits; //A visit is a date and a point in time
	private PatientInfo c;
	
	public PatientInfo getPatientInfo(){
		return c;
	}
	
	public void PatientInfo(PatientInfo c){
		this.c = c;
	}
	
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

	public List<String> getAllVisits() {
		return allVisits;
	}

	public void setAllVisits(List<String> allVisits) {
		this.allVisits = allVisits;
	}

	public String getCurrentVisit() {
		return currentVisit;
	}

	public void setCurrentVisit(String currentVisit) {
		this.currentVisit = currentVisit;
	}
	
}
