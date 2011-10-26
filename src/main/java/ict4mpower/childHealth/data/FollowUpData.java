package ict4mpower.childHealth.data;

import java.io.Serializable;
import java.util.Date;

public class FollowUpData implements Serializable {
	private static final long serialVersionUID = -8893036385045807296L;
	
	private static FollowUpData instance = null;

	public static FollowUpData instance() {
		if(instance == null) {
			instance = new FollowUpData();
		}
		return instance;
	}
	
	private Date date;
	private String message;
	
	private FollowUpData() {}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}