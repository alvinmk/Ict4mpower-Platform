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
package ict4mpower;

import java.util.List;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import models.PatientInfo;
/*
 * What should be stored in the session
 */
public class AppSession extends WebSession{
	private static final long serialVersionUID = 1L;
	private String userID; //An Id for the current user
	private String goal; //Current goal
	private String task; //Current task
	private String[] roles;
	private long currentVisit;
	private List<Long> allVisits; //A visit is a date and a point in time
	private PatientInfo patientInfo;
	
	public PatientInfo getPatientInfo(){
		return this.patientInfo;
	}
	
	public void setPatientInfo(PatientInfo patientInfo){
		this.patientInfo = patientInfo;
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

	public List<Long> getAllVisits() {
		return allVisits;
	}

	public void setAllVisits(List<Long> allVisits) {
		this.allVisits = allVisits;
	}

	public long getCurrentVisit() {
		return currentVisit;
	}

	public void setCurrentVisit(long currentVisit) {
		this.currentVisit = currentVisit;
	}
	
}
